package net.seehope.springsecurity.properties;

import lombok.Data;

/**
 * @author JoinYang
 * @date 2022/2/16 12:23
 */
@Data
//存放与验证码相关的变量
public class ImageValidateCodeProperties {
    private int width = 67;
    private int height = 23;
    private int length = 4;
    private int expireIn = 15*60;
    //放置所有需要验证码的页面
    private String validateUrl="";
}
