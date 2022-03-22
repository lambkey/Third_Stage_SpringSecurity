package net.seehope.springsecurity.mapper;

import net.seehope.springsecurity.pojo.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

/**
 * @author JoinYang
 * @date 2022/2/11 12:43
 */
public interface UserMapper extends Mapper<User> {

    @Select("SELECT * FROM user where username=#{username}")
    User selectByID(@Param("username") String username);

    @Select("SELECT * FROM user where mobile=#{mobile}")
    User selectByMobile(@Param("mobile") String mobile);


}
