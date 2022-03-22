package net.seehope.springsecurity.service.imp;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import net.seehope.springsecurity.mapper.UserMapper;
import net.seehope.springsecurity.pojo.User;
import net.seehope.springsecurity.properties.ProjectConstant;
import net.seehope.springsecurity.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.social.security.SocialUser;
import org.springframework.social.security.SocialUserDetails;
import org.springframework.social.security.SocialUserDetailsService;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;
import java.util.List;

/**
 * @author JoinYang
 * @date 2022/2/11 12:57
 */
@Service
public class UserServiceImp implements IUserService, UserDetailsService, SocialUserDetailsService {


    @Autowired
    private UserMapper userMapper;

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public List<User> queryAllUser() {
        return userMapper.selectAll();
    }

    @Override
    public int insertUser(User user) {
        return userMapper.insert(user);
    }

    @Override
    //传进来的是用户名
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {

        if (request.getRequestURI().equals(ProjectConstant.lOGIN_SSM_PROCESS_URL)) {
            User user2 = userMapper.selectByMobile(s);
            if (user2!=null) {
                User user=user2;
                UserDetails userDetails = new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), true, true,
                        true, true, AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_USER"));
                //因为加入了密码验证,所以要把输出得到的字符串注入到数据库，才可以完成验证登录
                //System.out.println(passwordEncoder.encode(user.getPassword()));
                return userDetails;
            }
        }else {
            User user1 = userMapper.selectByID(s);
            //System.out.println("???");
            if (user1 != null) {
                User user = user1;
                UserDetails userDetails = new org.springframework.security.core.userdetails
                        .User(user.getUsername(), user.getPassword(), true, true,
                        true, true, AuthorityUtils.commaSeparatedStringToAuthorityList(" ROLE_USER"));

                //因为加入了密码验证,所以要把输出得到的字符串注入到数据库，才可以完成验证登录
                //System.out.println(passwordEncoder.encode(user.getPassword()));
                return userDetails;
            }
            }

            throw  new UsernameNotFoundException("用户没有找到");

    }


    @Override
    public SocialUserDetails loadUserByUserId(String userid) throws UsernameNotFoundException {
        Example example = new Example(User.class);
        example.createCriteria().andEqualTo("id", userid);
        List<User> users = userMapper.selectByExample(example);
        if (users.size() != 0) {
            User user = users.get(0);
            SocialUser userDetails = new SocialUser(user.getId() + "", user.getPassword(), true, true, true, true,
                    AuthorityUtils.commaSeparatedStringToAuthorityList("admin,ROLE_USER"));
            return userDetails;
        }

        throw new UsernameNotFoundException("username not found");
    }
}
