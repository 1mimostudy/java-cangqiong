package com.sky.mapper.service.impl;

import com.sky.entity.Dish;
import com.sky.entity.DishFlavor;
import com.sky.entity.dto.DishDTO;
import com.sky.mapper.DishFlavorMapper;
import com.sky.mapper.DishMapper;
import com.sky.mapper.service.DishService;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Select;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * ClassName: DishServiceImpl
 * Package: com.sky.mapper.service.impl
 * Description:
 *
 * @Author 尚硅谷-宋红康
 * @Create 2026/1/28 11:04
 * @Version 1.0
 */
@Slf4j
@Service
public class DishServiceImpl implements DishService {
     @Autowired
     private DishMapper dishMapper;
    @Autowired
    private DishFlavorMapper dishFlavorMapper;

    @Override
    @Transactional
    public void saveWithFlavor(DishDTO dishDTO){
        Dish dish = new Dish();
        BeanUtils.copyProperties(dishDTO,dish);
        dishMapper.insert(dish);
        //获取insert语句生成主键值
        Long dishId = dish.getId();
        List<DishFlavor> flavors = dishDTO.getFlavors();
        if(flavors != null && flavors.size() > 0){
        flavors.forEach(dishFlavor -> {
            dishFlavor.setDishId(dishId);
        });
        dishFlavorMapper.insertBatch(flavors);
            }
        }



    }

