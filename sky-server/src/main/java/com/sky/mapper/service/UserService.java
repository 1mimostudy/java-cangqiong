package com.sky.mapper.service;

import com.sky.entity.User;
import com.sky.entity.dto.UserLoginDTO;

/**
 * ClassName: UserService
 * Package: com.sky.mapper.service
 * Description:
 *
 * @Author 尚硅谷-宋红康
 * @Create 2026/3/5 15:43
 * @Version 1.0
 */
public interface UserService {

    User wxlogin(UserLoginDTO  userLoginDTO);
}
