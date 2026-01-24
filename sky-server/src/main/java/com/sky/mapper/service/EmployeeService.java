package com.sky.mapper.service;

import com.sky.entity.dto.EmployeeDTO;
import com.sky.entity.dto.EmployeeLoginDTO;
import com.sky.entity.Employee;

public interface EmployeeService {

    /**
     * 员工登录
     * @param employeeLoginDTO
     * @return
     */
    Employee login(EmployeeLoginDTO employeeLoginDTO);
    /*
    * 新增员工*/
   public void save(EmployeeDTO employeeDTO);
}
