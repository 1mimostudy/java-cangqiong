package com.sky.mapper.service;

import com.sky.entity.Setmeal;
import com.sky.entity.dto.SetmealDTO;
import com.sky.entity.dto.SetmealPageQueryDTO;
import com.sky.result.PageResult;
import com.sky.vo.DishItemVO;
import com.sky.vo.SetmealVO;

import java.util.List;

/**
 * ClassName: SetmealService
 * Package: com.sky.mapper.service
 * Description:
 *
 * @Author 尚硅谷-宋红康
 * @Create 2026/3/2 16:16
 * @Version 1.0
 */
public interface SetmealService {
    /**
     * 新增套餐
     * @param setmealDTO
     */


    void saveWithDish(SetmealDTO setmealDTO);

    PageResult pageQuery(SetmealPageQueryDTO setmealPageQueryDTO);

    void deleteBatch(List<Long> ids);

    void update(SetmealDTO setmealDTO);



    SetmealVO getByIdWithDish(Long id);

    void startOrStop(Integer status, Long id);

    /**
     * 条件查询
     * @param setmeal
     * @return
     */
    List<Setmeal> list(Setmeal setmeal);

    /**
     * 根据id查询菜品选项
     * @param id
     * @return
     */
    List<DishItemVO> getDishItemById(Long id);
}
