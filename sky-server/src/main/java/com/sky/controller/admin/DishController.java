package com.sky.controller.admin;

import com.sky.entity.dto.DishDTO;
import com.sky.mapper.service.DishService;
import com.sky.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * ClassName: DishController
 * Package: com.sky.controller.admin
 * Description:
 *
 * @Author 尚硅谷-宋红康
 * @Create 2026/1/28 10:57
 * @Version 1.0
 */
@RestController
@Slf4j
@RequestMapping("/admin/dish")
@Api(tags="菜品相关接口")
public class DishController {
    @Autowired
    private DishService dishService;
    @PostMapping
    @ApiOperation("新增菜品")
    public Result save(@RequestBody DishDTO   dishDTO){
        log.info("新增菜品：{}",dishDTO);
        return Result.success();
    }

}
