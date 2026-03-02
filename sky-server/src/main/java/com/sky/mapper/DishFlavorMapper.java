package com.sky.mapper;

import com.sky.entity.DishFlavor;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * ClassName: DishFlavorMapper
 * Package: com.sky.mapper
 * Description:
 *
 * @Author 尚硅谷-宋红康
 * @Create 2026/1/28 11:15
 * @Version 1.0
 */
@Mapper
public interface DishFlavorMapper {
     void   insertBatch(List<DishFlavor> dishFlavors);
     @Delete("delete from dish_flavor where dish_id =#{dishId}")
     void deleteByDishId(Long id);
}
