package com.jeffery.filter;

import com.alibaba.fastjson.JSONObject;
import com.jeffery.pojo.Result;
import com.jeffery.utils.JwtUtils;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import java.io.IOException;

@Slf4j
@WebFilter(urlPatterns = "/*")
public class LoginFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        //获取请求url
        String url = request.getRequestURL().toString();
        //判断url是否含有login 若包含则放行
        if(url.contains("login")) {
            filterChain.doFilter(request, response);
            return;
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
            return;
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
            return;
        }
        //放行
        filterChain.doFilter(request,response);
    }
}
