package net.seehope.springsecurity.web.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import java.io.IOException;



/**
 * @author JoinYang
 * @date 2022/2/13 13:47
 */
//日志输出组件，起于lombok
@Slf4j
public class TimeFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        //放行前
        long start = System.currentTimeMillis();
        log.info("The start doFilter time is {}",start);
        //放行
        filterChain.doFilter(servletRequest, servletResponse);
        //放行之后
        long end = System.currentTimeMillis();
        log.info("The end doFilter time is {}",end);
        log.info("The total time is{}",end-start);
    }

    @Override
    public void destroy() {

    }
}
