package net.seehope.springsecurity.web.controller;


import lombok.AllArgsConstructor;
import net.seehope.springsecurity.pojo.User;
import net.seehope.springsecurity.pojo.vo.UserVo;
import net.seehope.springsecurity.service.imp.UserServiceImp;
import net.seehope.springsecurity.util.JSONResult;
import net.seehope.springsecurity.web.annotation.UserIsExistOrNoExistAnnotation;
import org.springframework.beans.BeanUtils;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


/**
 * @author JoinYang
 * @date 2022/2/10 16:37
 */
@RequestMapping( "/user")
@RestController
@AllArgsConstructor
public class UserController {

    private UserServiceImp userServiceImp;

    @GetMapping
    public JSONResult queryAllUser(){
        return JSONResult.ok(userServiceImp.queryAllUser());
    }

    @PostMapping
    public JSONResult insertUser(@RequestBody @Valid UserVo userVo){
        //BeanUtils.copyProperties(a,b) b中的属性在a中一定含有才能实现两个类的转换
        User user=new User();
        BeanUtils.copyProperties(userVo,user);
        return JSONResult.ok(userServiceImp.insertUser(user));
    }

    @GetMapping("/me")
    //将用户登录成功的凭证返回给前端，这是security框架的检测，自动注入Authentication
    public Object getNowUserAuthentication(Authentication authentication){
        return authentication;
    }
}
