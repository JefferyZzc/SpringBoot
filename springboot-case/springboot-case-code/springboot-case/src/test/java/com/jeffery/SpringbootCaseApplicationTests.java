package com.jeffery;

import io.jsonwebtoken.*;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

//@SpringBootTest
class SpringbootCaseApplicationTests {

    @Test
    void contextLoads() {

    }

    @Test
    public void testGetJwt(){
        Map<String, Object> claims = new HashMap<>();
        claims.put("id",1);
        claims.put("name","Tom");
        String jwt = Jwts.builder()
                .signWith(SignatureAlgorithm.HS256,"jeffery")//签名算法
                .setClaims(claims)//自定义内容
                .setExpiration(new Date(System.currentTimeMillis()+3600*1000))//设置有效期为一个小时
                .compact();
        System.out.println(jwt);
    }

    @Test
    public void testParseJwt(){
        Claims claims = Jwts.parser()
                .setSigningKey("jeffery")//指定密钥
                //传递jwt令牌
                .parseClaimsJws("eyJhbGciOiJIUzI1NiJ9.eyJuYW1lIjoiVG9tIiwiaWQiOjEsImV4cCI6MTY5NzcwNTM1Mn0.snA7ur57NeIgOQcgO_zq2xF7rIpapqPeAWPAU2InMeg")
                //.getHeader();
                .getBody();//拿到第二部分自定义内容
                //.getSignature();
        System.out.println(claims);
    }
}
