package net.seehope.springsecurity;

import net.seehope.springsecurity.authentication.AuthenticationSuccessHandler;
import net.seehope.springsecurity.authentication.ssm.SsmValidateCodeAuthenticationConfig;
import net.seehope.springsecurity.configuration.FormAuthenticationConfig;
import net.seehope.springsecurity.configuration.authorize.AuthorizeConfigManager;
import net.seehope.springsecurity.validate.validatecodeconfig.ValidateCodeSecurityConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

/**
 * @author JoinYang
 * @date 2022/3/11 21:10
 */

//提供资源服务
@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {
    @Autowired
    private AuthenticationSuccessHandler authenticationSuccessHandler;
    @Autowired
    private SsmValidateCodeAuthenticationConfig ssmValidateCodeAuthenticationConfig;
    @Autowired
    private ValidateCodeSecurityConfig validateCodeSecurityConfig;
    @Autowired
    private FormAuthenticationConfig formAuthenticationConfig;
    @Autowired
    private AuthorizeConfigManager authorizeConfigManager;
    @Override
    public void configure(HttpSecurity http) throws Exception {
//        http.formLogin().successHandler(authenticationSuccessHandler).and().authorizeRequests().anyRequest().authenticated();
        http.apply(ssmValidateCodeAuthenticationConfig);//手机端验证码配置
        http.apply(validateCodeSecurityConfig);//验证码配置
        formAuthenticationConfig.configure(http);//表单登录配置
        authorizeConfigManager.config(http.authorizeRequests());//访问授权
    }
}
