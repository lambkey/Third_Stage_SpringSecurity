package net.seehope.springsecurity.configuration;

import lombok.AllArgsConstructor;
import net.seehope.springsecurity.properties.ProjectConstant;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

/**
 * @author JoinYang
 * @date 2022/3/12 14:18
 */
@AllArgsConstructor
@Configuration
//表单认证模块
public class FormAuthenticationConfig {

    private AuthenticationFailureHandler authenticationFailureHandler;
    private AuthenticationSuccessHandler authenticationSuccessHandler;

    public void configure(HttpSecurity httpSecurity) throws Exception{
        httpSecurity.formLogin()
                .loginPage(ProjectConstant.LOGIN_PAGE)//用户没有登录时，访问任何资源都会跳到该路径
                .loginProcessingUrl(ProjectConstant.IMAGE_PROCESSING_URL)//这个和表单的action一致,formLogin默认是访问/login
                .failureHandler(authenticationFailureHandler)
                .successHandler(authenticationSuccessHandler);
    }
}
