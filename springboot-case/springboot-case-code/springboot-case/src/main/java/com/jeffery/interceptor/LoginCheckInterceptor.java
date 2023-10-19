package com.jeffery.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.jeffery.pojo.Result;
import com.jeffery.utils.JwtUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Component
public class LoginCheckInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //获取请求url
        String url = request.getRequestURL().toString();
        //判断url是否含有login 若包含则放行
        if(url.contains("login")) {
            return true ;
        }
        //获取请求的请求头（token）
        String jwt = request.getHeader("token");
        //判断token是否存在，若无，返回登录错误
        if(!StringUtils.hasLength(jwt)){
            Result error = Result.error("NOT_LOGIN");
            //将对象装换为json
            String notLogin = JSONObject.toJSONString(error);
            //getwriter获取输出流 write响应字符串给浏览器
            response.getWriter().write(notLogin);
            return false;
        }
        //解析token，若无，返回登录错误
        try {
            JwtUtils.parseJwt(jwt);
        } catch (Exception e) {
            e.printStackTrace();
            Result error = Result.error("NOT_LOGIN");
            //将对象装换为json
            String notLogin = JSONObject.toJSONString(error);
            //getwriter获取输出流 write响应字符串给浏览器
            response.getWriter().write(notLogin);
            return false;
        }
        //放行
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("postHandle..");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("afterComplecation");
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
