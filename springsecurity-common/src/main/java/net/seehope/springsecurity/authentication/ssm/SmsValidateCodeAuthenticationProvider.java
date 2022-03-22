package net.seehope.springsecurity.authentication.ssm;

import lombok.Data;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;


/**
 * @author JoinYang
 * @date 2022/3/4 20:38
 */
//仿照AbstractUserDetailsAuthenticationProvider的写法
// Provider是用来校验自己的手机号信息
//在校验过程中Provider会调用UserDetailsService，UserDetailService会读取用户信息，判断是否可以登录
//如果登录成功就把SsmValidateCodeAuthenticationToken标记为已认证的
@Data
public class SmsValidateCodeAuthenticationProvider implements AuthenticationProvider {


    private UserDetailsService userDetailsService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

       SsmValidateCodeAuthenticationToken token= (SsmValidateCodeAuthenticationToken)authentication;

       String mobile = (String) token.getPrincipal();

       UserDetails user = userDetailsService.loadUserByUsername(mobile);

       if (user==null){
           throw new InternalAuthenticationServiceException("未找到当前用户的手机号");
       }

       SsmValidateCodeAuthenticationToken authenticationResult = new SsmValidateCodeAuthenticationToken(mobile,user.getAuthorities());

        return authenticationResult;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return SsmValidateCodeAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
