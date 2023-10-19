package com.jeffery.controller;

import com.jeffery.pojo.Emp;
import com.jeffery.pojo.Result;
import com.jeffery.service.EmpService;
import com.jeffery.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
public class loginController {
    @Autowired
    EmpService empService;

    @PostMapping("/login")
    public Result login(@RequestBody Emp emp){
        log.info("员工登录#{}",emp);
        Emp e = empService.login(emp);

        //登陆成功 生成令牌并返回令牌
        if(e!=null){
            Map<String,Object> claims = new HashMap<>();
            claims.put("id",emp.getId());
            claims.put("name",emp.getName());
            claims.put("username",emp.getUsername());
            String jwt = JwtUtils.generateJwt(claims);
            return Result.success(jwt);
        }

        //登陆失败返回错误信息
        return Result.error("用户民或密码错误");
    }
}
