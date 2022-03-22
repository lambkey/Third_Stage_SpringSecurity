package net.seehope.springsecurity;

import net.seehope.springsecurity.web.filter.TimeFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author JoinYang
 * @date 2022/2/13 14:02
 */
//@Configuration
public class RegistryOfFilter {
    @Bean
    public FilterRegistrationBean timeFilter(){
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        TimeFilter timeFilter = new TimeFilter();
        filterRegistrationBean.setFilter(timeFilter);
        List<String> url=new ArrayList<>();
        //添加要拦截的路径进行放行计时
        url.add("/hello");
        filterRegistrationBean.setUrlPatterns(url);
        return filterRegistrationBean;
    }
}
