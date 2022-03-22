package net.seehope.springsecurity.web.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author JoinYang
 * @date 2022/2/13 14:30
 */
//Mvc
@Controller
@Slf4j
public class TimeInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        //放行前
        long start = System.currentTimeMillis();
        log.info("The start TimeInterceptor time is {}",start);
        httpServletRequest.setAttribute("start",start);
        //只有return true下面的方法才会执行
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
        //放行之后
        long end = System.currentTimeMillis();
        log.info("The end TimeInterceptor time is {}",end);
        long start=(Long) httpServletRequest.getAttribute("start");
        log.info("The Time Interceptor total time is{}",end-start);
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
        //异常结束输出
        System.out.println("TimeInterceptor");
    }
}
