package net.seehope.springsecurity.service;

import net.seehope.springsecurity.pojo.User;

import java.util.List;

/**
 * @author JoinYang
 * @date 2022/2/11 12:54
 */
public interface IUserService {
    List<User> queryAllUser();
    int insertUser(User user);
}
