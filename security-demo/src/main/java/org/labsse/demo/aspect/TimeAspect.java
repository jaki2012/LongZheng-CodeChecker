package org.labsse.demo.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * @author lijiechu
 * @create on 2018/12/25
 * @description
 */
//@Aspect
//@Component
public class TimeAspect {

    @Around("execution(* org.labsse.demo.controller.UserController.*(..))")
    public Object handleControllerMethod(ProceedingJoinPoint pjp) throws Throwable {
        System.out.println("time aspect start");
        Object[] args = pjp.getArgs();
        for(Object arg: args) {
            System.out.println("arg is:" + arg);
        }
        long start = System.currentTimeMillis();
        Object object = pjp.proceed();
        System.out.println("time aspect 耗时：" + (System.currentTimeMillis() - start));
        return object;
    }
}
