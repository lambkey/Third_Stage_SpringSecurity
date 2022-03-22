package net.seehope.springsecurity;

import lombok.AllArgsConstructor;

import net.seehope.springsecurity.authentication.LoginFaliureHandler;
import net.seehope.springsecurity.authentication.LoginSuccessHandler;
import net.seehope.springsecurity.authentication.ssm.SsmValidateCodeAuthenticationConfig;
import net.seehope.springsecurity.configuration.FormAuthenticationConfig;
import net.seehope.springsecurity.configuration.authorize.AuthorizeConfigManager;



import net.seehope.springsecurity.properties.ProjectProperties;
import net.seehope.springsecurity.social.SocialAuthenticationConfig;
import net.seehope.springsecurity.validate.validatecodeconfig.ValidateCodeSecurityConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.sql.DataSource;
import java.util.Map;

/**
 * @author JoinYang
 * @date 2022/2/14 10:12
 * 登录模块
 */
@Configuration
@AllArgsConstructor
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    //使访问域名由原来的常量变为变量
    private ProjectProperties properties;

    private UserDetailsService userServiceImp;

    private DataSource dataSource;

    private SsmValidateCodeAuthenticationConfig ssmValidateCodeAuthenticationConfig;

    private ValidateCodeSecurityConfig validateCodeSecurityConfig;

    private FormAuthenticationConfig formAuthenticationConfig;

    private AuthorizeConfigManager authorizeConfigManager;
    //使用BCryptPasswordEncoder实现密码加密和验证
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    //记住我的选择
    //静态文件一定要把name=“remember-me”
    @Bean
    public PersistentTokenRepository persistentTokenRepository(){
        JdbcTokenRepositoryImpl tokenRepository=new JdbcTokenRepositoryImpl();
        tokenRepository.setDataSource(dataSource);
        //初始化会在数据库创建一张表，这个只能运行一次
        //tokenRepository.setCreateTableOnStartup(true);
        return tokenRepository;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        SocialAuthenticationConfig socialAuthenticationConfig =new SocialAuthenticationConfig(properties.getSocial().getProcessingUrl());
            http.apply(socialAuthenticationConfig);
            http.apply(validateCodeSecurityConfig);//验证码登录配置
            http.apply(ssmValidateCodeAuthenticationConfig);//短信验证码登录配置
            formAuthenticationConfig.configure(http);//表单登录配置
            authorizeConfigManager.config(http.authorizeRequests());//授权配置

            http
                .rememberMe()//记住我的功能
                .tokenRepository(persistentTokenRepository())
                .tokenValiditySeconds(properties.getWebProperties().getTokenValiditySeconds())//记住的时间期限
                .userDetailsService(userServiceImp)//指定当放回了username之后用哪个UserDetails做认证

//                .authorizeRequests()
//                .antMatchers(ProjectConstant.LOGIN_PAGE,ProjectConstant.USER_LOGIN_STATIC_PAGE,ProjectConstant.IMAGE_PROCESSING_URL,ProjectConstant.lOGIN_SSM_PROCESS_URL,ProjectConstant.VALIDATE_CODE_URL+"/*")//对此路径放行
//                .permitAll()//如果访问的路径是antMatchers里面的路径则不用登录认证
//                .anyRequest()//任何请求
//                .authenticated()//都需要通过身份验证
                .and()
                .csrf().disable();//关闭跨站攻击功能



    }
    @Override
    public void configure(WebSecurity web) throws Exception {
        //解决静态css,image,js资源被拦截的问题
        web.ignoring().antMatchers("/css/**","/image/**");
    }
}
