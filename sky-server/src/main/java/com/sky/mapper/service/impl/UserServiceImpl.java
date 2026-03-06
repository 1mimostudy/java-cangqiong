package com.sky.mapper.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.sky.constant.MessageConstant;
import com.sky.entity.User;
import com.sky.entity.dto.UserLoginDTO;
import com.sky.exception.LoginFailedException;
import com.sky.mapper.UserMapper;
import com.sky.mapper.service.UserService;
import com.sky.properties.WeChatProperties;
import com.sky.utils.HttpClientUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.descriptor.web.WebXml;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;


import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * ClassName: UserServiceImpl
 * Package: com.sky.mapper.service.impl
 * Description:
 *
 * @Author 尚硅谷-宋红康
 * @Create 2026/3/5 15:56
 * @Version 1.0
 */
@Service
@Slf4j
public class UserServiceImpl implements UserService {
    /**
     * 微信登录
     *
     * @param userLoginDTO
     * @return
     */
    public static final String WX_LOGIN = "https://api.weixin.qq.com/sns/jscode2session";
    @Autowired
    private WeChatProperties weChatProperties;
    @Autowired
    private UserMapper userMapper;
    @Override
    public User wxlogin(UserLoginDTO userLoginDTO) {
        //调用微信登录接口，获取openid
        String openid = getOpenid(userLoginDTO.getCode());
        //判断openid是否为空，为空则登录失败
        if (openid == null){
            throw new LoginFailedException(MessageConstant.LOGIN_FAILED);
        }

        //根据openid查询数据库，判断该用户是否存在
        User user = userMapper.getByOpenid(openid);
        //如果是新用户，自动完成注册
        if(user == null){
            user =User.builder()
                    .openid(openid)
                    .createTime(LocalDateTime.now())
                    .build();
            userMapper.insert(user);
        }
        //返回这个用户对象


        return user;
    }
    /**
     * 获取微信用户openid
     *
     * @param code
     * @return
     */
    private String getOpenid(String code) {
        //调用微信接口服务，获得openid
        Map<String, String> map = new HashMap<>();
        map.put("appid",weChatProperties.getAppid());
        map.put("secret",weChatProperties.getSecret());
        map.put("js_code",code);
        map.put("grant_type","authorization_code");
        String json = HttpClientUtil.doGet(WX_LOGIN, map);
        JSONObject jsonObject = JSON.parseObject(json);
        String openid = jsonObject.getString("openid");
        return openid;
    }
}
