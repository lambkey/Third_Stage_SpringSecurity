package net.seehope.springsecurity.authentication.ssm;

import net.seehope.springsecurity.properties.ProjectProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

/**
 * @author JoinYang
 * @date 2022/3/4 21:33
 */
//短信验证码认证模块
@Component
public class SsmValidateCodeAuthenticationConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

    @Autowired
    private AuthenticationSuccessHandler authenticationSuccessHandler;

    @Autowired
    private AuthenticationFailureHandler authenticationFailureHandler;

    @Autowired
    private ProjectProperties properties;

    @Autowired
    private UserDetailsService userDetailsService ;

    @Override
    public void configure(HttpSecurity builder) throws Exception {
        SsmValidateCodeAuthenticationFilter ssmValidateCodeAuthenticationFilter=
                new SsmValidateCodeAuthenticationFilter();

        ssmValidateCodeAuthenticationFilter.setProjectProperties(properties);

        ssmValidateCodeAuthenticationFilter.setAuthenticationManager(builder.getSharedObject(AuthenticationManager.class));

        ssmValidateCodeAuthenticationFilter.setAuthenticationFailureHandler(authenticationFailureHandler);

        ssmValidateCodeAuthenticationFilter.setAuthenticationSuccessHandler(authenticationSuccessHandler);

        SmsValidateCodeAuthenticationProvider smsValidateCodeAuthenticationProvider=
                new SmsValidateCodeAuthenticationProvider();
        smsValidateCodeAuthenticationProvider.setUserDetailsService(userDetailsService);

        builder.authenticationProvider(smsValidateCodeAuthenticationProvider).addFilterAfter(ssmValidateCodeAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
    }
}
