package net.seehope.springsecurity.validate.icode;

import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * @author JoinYang
 * @date 2022/3/1 14:53
 * 所有验证码的创建方法
 */
public interface ValidateCodeProcessor {

    //创建验证码
    void create(ServletWebRequest request) throws Exception;

    //验证码的类型：短信，图片或者其他
     boolean support(String type);

     //验证验证码
    void validate(ServletWebRequest request) throws ServletRequestBindingException;
}
