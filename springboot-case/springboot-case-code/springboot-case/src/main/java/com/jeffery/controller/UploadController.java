package com.jeffery.controller;


import com.aliyuncs.exceptions.ClientException;
import com.jeffery.pojo.Result;
import com.jeffery.utils.AliOSSUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Slf4j
@RestController

public class UploadController {

    @Autowired
    private AliOSSUtils aliOSSUtils;
    /*@PostMapping("/upload")
    public Result upload(String username, Integer age, MultipartFile image) throws IOException {
        log.info("上传数据 {} {} {}",username,age,image);

        String originalFilename = image.getOriginalFilename();

        int index = originalFilename.lastIndexOf(".");//分割最后一个.后的名字
        String extname = originalFilename.substring(index);//拼接index
        String newFilename = UUID.randomUUID().toString()+extname;//将UUID与extname拼接

        image.transferTo(new File("D:\\"+newFilename));

        return Result.success();
    }*/
    @PostMapping("/upload")
    public Result upload(MultipartFile image) throws IOException, ClientException {
        log.info("文件名字{}",image.getOriginalFilename());
        String url = aliOSSUtils.upload(image) ;
        log.info("文件上传成功，url={}",url);
        return Result.success(url);
    }
}
