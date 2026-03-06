package com.sky.mapper.service;


import com.sky.entity.Dish;
import com.sky.entity.dto.DishDTO;
import com.sky.entity.dto.DishPageQueryDTO;
import com.sky.result.PageResult;
import com.sky.vo.DishVO;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public interface DishService {
    /**
     * 新增菜品和对应的口味数据
     * @param dishDTO
     */
    public void saveWithFlavor(DishDTO dishDTO);

    PageResult pageQuery(DishPageQueryDTO dishPageQueryDTO);

    void deleteBatch(List<Long> ids);
    //根据id查询菜品和对应的口味数据
    DishVO getByIdWithFlavor(Long id);
    //修改菜品和口味数据

    void updateWithFlavor(DishDTO dishDTO);

    List<Dish> list(Long categoryId);
    /**
     * 条件查询菜品和口味
     * @param dish
     * @return
     */
    List<DishVO> listWithFlavor(Dish dish);
}
