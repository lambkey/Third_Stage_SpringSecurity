package net.seehope.springsecurity.social.qq;

import lombok.Data;

/**
 * @author JoinYang
 * @date 2022/3/14 19:46
 */
@Data
public class QQUserInfo {
    //返回码
    private String ret;

    //如果ret<0，会有相应的错误信息提示,返回数据全部用UTF-8编码
    private String msg;


    private String OpenId;

    //不知道是什么东西,文档上没有写,但是实际上api返回里有
    private String is_lost;

    //省,直辖市
    private String province;

    //市，直辖市区
    private String city;

    //出生年月
    private String year;

    //用户在QQ空间上面的昵称
    private String nickname;

    //大小为30*30像素的QQ空间头像URL
    private String figureurl;

    //大小为50*50像素的QQ空间头像URL
    private String figureurl_1;


    //大小为100*100像素的QQ空间头像URL
    private String figureurl_2;

    //大小为40*40像素的QQ头像URL
    private String figureurl_qq_1;

    //大小为100*100像素的QQ头像URL
    private String figureurl_qq_2;

    //性别，如果获取不到则默认返回是男
    private String gender;

    //标识用户是否为黄钻用户,0:不是,1:是
    private String is_yellow_vip;
}
