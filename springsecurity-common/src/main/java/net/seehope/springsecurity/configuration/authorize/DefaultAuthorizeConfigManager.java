package net.seehope.springsecurity.configuration.authorize;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;

import java.util.List;

/**
 * @author JoinYang
 * @date 2022/3/12 15:09
 */
@Configuration
@AllArgsConstructor
public class DefaultAuthorizeConfigManager implements AuthorizeConfigManager{

    private List<AuthorizeProvider> authorizeProviders;

    @Override
    public void config(ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry config) {
        boolean existAnyRequestConfig = false;
        String existAnyRequestConfigName =null;

        for (AuthorizeProvider authorizeProvider : authorizeProviders) {
           boolean isAnyRequest = authorizeProvider.config(config);
           if (isAnyRequest&&existAnyRequestConfig){
               throw new RuntimeException("重复的anyRequest:"+existAnyRequestConfigName);
           }else if (isAnyRequest){
               existAnyRequestConfig=true;
               existAnyRequestConfigName=authorizeProvider.getClass().getName();
           }
        }
        if(!existAnyRequestConfig){
            config.anyRequest().authenticated();
        }
    }
}
