package net.seehope.springsecurity;

import lombok.AllArgsConstructor;
import net.seehope.springsecurity.web.interceptor.TimeInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @author JoinYang
 * @date 2022/2/13 14:40
 */
//webMVC
//@Configuration
@AllArgsConstructor
public class RegistryOfInterceptor extends WebMvcConfigurerAdapter {
    private TimeInterceptor timeInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //默认拦截所有请求
        registry.addInterceptor(timeInterceptor);
    }
}
