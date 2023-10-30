package com.sky.controller.admin;

import com.sky.dto.DishDTO;
import com.sky.dto.DishPageQueryDTO;
import com.sky.entity.Dish;
import com.sky.result.PageResult;
import com.sky.result.Result;
import com.sky.service.DishService;
import com.sky.vo.DishVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@Api(tags = "菜品相关接口")
@Slf4j
@RestController
@RequestMapping("/admin/dish")
public class DishController {
    @Autowired
    DishService dishService;

    @Autowired
    RedisTemplate redisTemplate;

    @PostMapping
    @ApiOperation("新增菜品")
    public Result save(@RequestBody DishDTO dishDTO){
        log.info("新增菜品{]",dishDTO);
        dishService.saveWithFlavor(dishDTO);

        String key = "dish_"+dishDTO.getCategoryId();
        cleanCache(key);

        return Result.success();
    }

    @GetMapping("/page")
    @ApiOperation("菜品分页查询")
    public Result<PageResult> page(DishPageQueryDTO dishPageQueryDTO){
        log.info("菜品分页查询");
        PageResult pageResult = dishService.pageQuery(dishPageQueryDTO);
        return Result.success(pageResult);
    }

    @ApiOperation("菜品的批量删除")
    @DeleteMapping()
    public Result delete(@RequestParam List<Long> ids){
        log.info("菜品的批量删除");
        dishService.deleteBatch(ids);

        cleanCache("dish_*");

        return Result.success();
    }

    @GetMapping("/{id}")
    @ApiOperation("根据id查询菜品")
    public Result<DishVO> getById(@PathVariable Long id){
        log.info("根据id查询菜品");
        DishVO dishVo = dishService.getByIdWithFlavor(id);
        return Result.success(dishVo);
    }

    @PutMapping
    @ApiOperation("修改菜品")
    public Result update(@RequestBody DishDTO dishDTO){
        log.info("修改菜品{}",dishDTO);
        dishService.updateWithFlavor(dishDTO);

        cleanCache("dish_*");

        return Result.success();
    }

    @PostMapping("/status/{status}")
    @ApiOperation("菜品起售停售")
    public Result<String> startOrStop(@PathVariable Integer status,Long id){
        dishService.startOrStop(status,id);
        cleanCache("dish_*");
        return Result.success();
    }

    @GetMapping("/list")
    @ApiOperation("根据分类id查询菜品")
    public Result<List<Dish>> list(Long categoryId){
        List<Dish> list = dishService.list(categoryId);
        return Result.success(list);
    }


    public void cleanCache(String pattern){
        Set keys = redisTemplate.keys(pattern);
        redisTemplate.delete(keys);
    }

}
