package net.seehope.springsecurity.validate.validatecodeconfig;

import net.seehope.springsecurity.authentication.ssm.SmsValidateCodeAuthenticationProvider;
import net.seehope.springsecurity.authentication.ssm.SsmValidateCodeAuthenticationFilter;
import net.seehope.springsecurity.properties.ProjectProperties;
import net.seehope.springsecurity.validate.ValidateCodeFilter;
import net.seehope.springsecurity.validate.icode.ValidateCodeProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author JoinYang
 * @date 2022/3/12 14:02
 */
@Component
public class ValidateCodeSecurityConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

    @Autowired
    private AuthenticationFailureHandler authenticationFailureHandler;

    @Autowired
    private Map<String, ValidateCodeProcessor> validateCodeProcessors;

    @Autowired
    private ProjectProperties properties;

    @Override
    public void configure(HttpSecurity builder) throws Exception {
        //在这里向验证码注入过滤器
        ValidateCodeFilter validateCodeFilter=new ValidateCodeFilter();
        validateCodeFilter.setProperties(properties);
        validateCodeFilter.afterPropertiesSet();//顺序是规定的
        validateCodeFilter.setValidateCodeProcessors(validateCodeProcessors);
        validateCodeFilter.setAuthenticationFailureHandler(authenticationFailureHandler);
        builder.addFilterBefore(validateCodeFilter,UsernamePasswordAuthenticationFilter.class);
    }
}