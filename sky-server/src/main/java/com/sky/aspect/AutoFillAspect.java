package com.sky.aspect;

import com.sky.annotation.AutoFill;
import com.sky.context.BaseContext;
import com.sky.enumeration.OperationType;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;
import org.aopalliance.intercept.Joinpoint;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.time.LocalDateTime;

import static com.sky.constant.AutoFillConstant.*;

/**
 * ClassName: AutoFillAspect
 * Package: com.sky.aspect
 * Description:
 *
 * @Author 尚硅谷-宋红康
 * @Create 2026/1/26 13:41
 * @Version 1.0
 */

// 切面类
    @Aspect
    @Component
    @Slf4j
public class AutoFillAspect {
    //切入点
    @Pointcut("execution(* com.sky.mapper.*.*(..)) &&  @annotation(com.sky.annotation.AutoFill)")
    public void autoFillPointcut() {
    }

    //前置通知
    @Before("autoFillPointcut()")
    public void autoFill(JoinPoint joinPoint) {
        log.info("开始进行公共字段填充");
        //获取到当前被拦截的方法上的数据库操作类型
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        AutoFill autoFill = signature.getMethod().getAnnotation(AutoFill.class);
        OperationType operationType = autoFill.value();
        //获取到当前被拦截的方法参数
        Object[] args = joinPoint.getArgs();
        if (args == null || args.length == 0) {
            return;
        }
        Object entity = args[0];
        //准备赋值数据
        LocalDateTime now = LocalDateTime.now();
        Long currentId = BaseContext.getCurrentId();
        //根据当前不同操作类型，给对应的属性通过反射字段赋值
        if (operationType == OperationType.INSERT) {
            try {
                // 设置创建时间和更新时间
                Method setCreateTime = entity.getClass().getDeclaredMethod(SET_CREATE_TIME, LocalDateTime.class);
                Method setUpdateTime = entity.getClass().getDeclaredMethod(SET_CREATE_USER, LocalDateTime.class);
                Method setCreateUser = entity.getClass().getDeclaredMethod(SET_UPDATE_TIME, Long.class);
                Method setUpdateUser = entity.getClass().getDeclaredMethod(SET_UPDATE_USER, Long.class);

                setCreateTime.invoke(entity, now);
                setUpdateTime.invoke(entity, now);
                setCreateUser.invoke(entity, currentId);
                setUpdateUser.invoke(entity, currentId);

            } catch (Exception e) {
                log.error("公共字段填充失败: {}", e.getMessage());
            }
        } else if (operationType == OperationType.UPDATE) {
            try {
                // 设置更新时间
                Method setUpdateTime = entity.getClass().getDeclaredMethod(SET_CREATE_USER, LocalDateTime.class);
                Method setUpdateUser = entity.getClass().getDeclaredMethod(SET_UPDATE_USER, Long.class);

                setUpdateTime.invoke(entity, now);
                setUpdateUser.invoke(entity, currentId);

            } catch (Exception e) {
                log.error("公共字段填充失败: {}", e.getMessage());
            }
        }
    }
}