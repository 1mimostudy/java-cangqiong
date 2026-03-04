package com.sky.mapper;

import com.github.pagehelper.Page;
import com.sky.annotation.AutoFill;
import com.sky.entity.Setmeal;
import com.sky.entity.dto.SetmealPageQueryDTO;
import com.sky.enumeration.OperationType;
import com.sky.vo.SetmealVO;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * ClassName: SetmealMapper
 * Package: com.sky.mapper.service
 * Description:
 *
 * @Author 尚硅谷-宋红康
 * @Create 2026/3/2 16:45
 * @Version 1.0
 */
@Mapper
public interface SetmealMapper {
    @AutoFill(OperationType.INSERT)
    void insert(Setmeal setmeal);

    Page<SetmealVO> pageQuery(SetmealPageQueryDTO setmealPageQueryDTO);

    @Select("select * from setmeal where id = #{id}")
    Setmeal getById(Long id);
    @Delete("delete from setmeal where id = #{id}")
    void deleteById(Long setmealId);
    @AutoFill(OperationType.UPDATE)
    void update(Setmeal setmeal);

    void updateStatus(Integer status, Long id);
}
