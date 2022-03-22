package net.seehope.springsecurity.configuration.authorize;

import net.seehope.springsecurity.properties.ProjectConstant;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;

/**
 * @author JoinYang
 * @date 2022/3/12 15:04
 */
//过滤链上要添加的组件（对请求进行规范），放行模块
@Configuration
public class DefaultAuthorizeConfigProvider implements AuthorizeProvider{
    @Override
    public boolean config(ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry config) {
       config.antMatchers(ProjectConstant.LOGIN_PAGE,ProjectConstant.USER_LOGIN_STATIC_PAGE,ProjectConstant.IMAGE_PROCESSING_URL,ProjectConstant.lOGIN_SSM_PROCESS_URL,ProjectConstant.VALIDATE_CODE_URL+"/*")//对此路径放行
                .permitAll()//如果访问的路径是antMatchers里面的路径则不用登录认证
                .anyRequest()//任何请求
                .authenticated();//都需要通过身份验证
        return true;
    }
}
