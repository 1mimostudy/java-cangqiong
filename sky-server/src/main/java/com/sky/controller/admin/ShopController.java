package com.sky.controller.admin;

import com.sky.result.Result;
import io.lettuce.core.models.role.RedisSentinelInstance;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.cache.CacheProperties;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

/**
 * ClassName: ShopController
 * Package: com.sky.controller.admin
 * Description:
 *
 * @Author 尚硅谷-宋红康
 * @Create 2026/3/4 19:26
 * @Version 1.0
 */
@RestController("adminShopController")
@RequestMapping("/admin/shop")
@Api(tags = "店铺相关接口")
@Slf4j
public class ShopController {
    public static final String KEY= "SHOP_STATUS";
    @Autowired
    private RedisTemplate redisTemplate;
    @PutMapping("/{status}")
    @ApiOperation("设置营业状态")
    public Result setStatus(@PathVariable Integer status) {
        log.info("设置店铺营业状态为：{}",status == 1 ? "营业中" : "打烊中");
        redisTemplate.opsForValue().set(KEY,status);
        return Result.success();
    }
    @GetMapping("/status")
    @ApiOperation("获取营业状态")
    public Result<Integer> getStatus() {
        redisTemplate.opsForValue().get(KEY);
        log.info("获取到营业状态");
        Integer status = (Integer) redisTemplate.opsForValue().get(KEY);
        return Result.success(status);
    }
}
