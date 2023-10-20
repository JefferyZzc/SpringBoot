package com.jeffery.controller;

import com.jeffery.anno.Log;
import com.jeffery.pojo.Dept;
import com.jeffery.pojo.Result;
import com.jeffery.service.DeptService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/depts")
public class DeptController {

    @Autowired
    private DeptService deptService;

   //private static Logger log = LoggerFactory.getLogger(DeptController.class);

    //@RequestMapping(value = "/depts",method = RequestMethod.GET)


    //查询全部部门
    @GetMapping
    public Result list(){
        log.info("查询全部部门数据");
        List<Dept> deptList = deptService.list();
        return Result.success(deptList);
    }

    //删除部门
    @Log
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id){
        log.info("根据id删除部门:{}",id);
        deptService.delete(id);
        return Result.success();
    }

    //添加部门
    @Log
    @PostMapping
    public Result add(@RequestBody Dept dept){
        log.info("新增部门{}",dept);
        deptService.add(dept);
        return Result.success();
    }
}
