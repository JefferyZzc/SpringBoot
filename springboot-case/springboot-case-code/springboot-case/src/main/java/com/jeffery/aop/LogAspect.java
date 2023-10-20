package com.jeffery.aop;

import com.alibaba.fastjson.JSONObject;
import com.jeffery.anno.Log;
import com.jeffery.mapper.OperateMapper;
import com.jeffery.pojo.OperateLog;
import com.jeffery.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;

@Component
@Aspect
@Slf4j
public class LogAspect {

    @Autowired
    HttpServletRequest httpServletRequest;

    @Autowired
    OperateMapper operateMapper;

    @Around("@annotation(com.jeffery.anno.Log)")
    public Object recordLog(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {


       //操作人id
       String jwt = httpServletRequest.getHeader("token");
       Claims claims = JwtUtils.parseJwt(jwt);
       Integer operatorUser = (Integer) claims.get("id");

       //操作类名
       LocalDateTime operatorTime =  LocalDateTime.now();

       //操作类名
       String className = proceedingJoinPoint.getTarget().getClass().getName();

       //操作方法名
       String methodName = proceedingJoinPoint.getSignature().getName();

       //操作方法参数
       Object[] args = proceedingJoinPoint.getArgs();
       String methodParams =  Arrays.toString(args);

       Long begin = System.currentTimeMillis();
       //方法执行返回值
       Object result = proceedingJoinPoint.proceed();
       Long end = System.currentTimeMillis();
       String returnValue = JSONObject.toJSONString(result);

       //操作耗时
       Long costTime = end - begin;

       OperateLog operateLog = new OperateLog(null,operatorUser,operatorTime,className,methodName,methodParams,returnValue,costTime);

       operateMapper.insert(operateLog);

       log.info("AOP日志记录{}",operateLog);
       return result;
    }
}
