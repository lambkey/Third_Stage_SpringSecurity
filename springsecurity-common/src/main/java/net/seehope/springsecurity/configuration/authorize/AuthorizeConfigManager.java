package net.seehope.springsecurity.configuration.authorize;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;

/**
 * @author JoinYang
 * @date 2022/3/12 14:57
 */
//收集容器中所有的provider
public interface AuthorizeConfigManager {
    //表示在实现这个配置的时候有没有anyRequest进行配置
    void config(ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry config);
}
