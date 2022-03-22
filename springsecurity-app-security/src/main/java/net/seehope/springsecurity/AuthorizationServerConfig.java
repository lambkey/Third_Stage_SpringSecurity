package net.seehope.springsecurity;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;

/**
 *@author JoinYang
 *@date 2022/3/11 20:56
 */
//1、获取授权码：http://localhost:8080/oauth/authorize?response_type=code&client_id=seehope&state=xyz&redirect_uri=http://www.baidu.com&scope=123
//2、获取token:http://localhost:8080/oauth/token?grant_type=authorization_code&code=8fpuL5&redirect_uri=http://www.baidu.com&client_id=seehope&scope=123
//   注意:获取token要携带通过BASE64编码拼接的client_id和client_secret
//3、用token获取系统中的资源
@EnableAuthorizationServer
@Configuration
public class AuthorizationServerConfig {

}
