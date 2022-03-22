package net.seehope.springsecurity.configuration;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * @author JoinYang
 * @date 2022/3/12 14:43
 */
public class DefaultUserDetailService implements UserDetailsService {
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        throw new UsernameNotFoundException("默认没有提供查询用户的业务层，请用户实现");
    }
}
