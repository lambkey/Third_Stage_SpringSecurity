package net.seehope.springsecurity.web.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @author JoinYang
 * @date 2022/2/13 15:16
 */
//@Aspect
@Component
@Slf4j
//和Filter、Interceptor的区别是不用再注册了
public class  TimeAspect {
    //*任意返回 ..*中间 .*包下所有的类 .(..)所有的方法参数
    //定义切点
    @Pointcut("execution(* net.seehope..*.web.controller.*.*(..))")
    public void timePointCut(){

    }
    @Around("timePointCut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        //放行前
        long start = System.currentTimeMillis();
        log.info("The start Aspect time is {}",start);
        Object result=point.proceed();
        //放行之后
        long end = System.currentTimeMillis();
        log.info("The end Aspect time is {}",end);
        log.info("The AspectTotal time is{}",end-start);
        return result;
    }
}
