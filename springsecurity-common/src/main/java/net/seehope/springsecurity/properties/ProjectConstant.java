package net.seehope.springsecurity.properties;

/**
 * @author JoinYang
 * @date 2022/2/15 14:55
 */
public class ProjectConstant {
    /*
    * 当用户没有访问权限的时候的响应
    *
    * */
    //图片验证码密码登录表单
    public static final String IMAGE_PROCESSING_URL = "/authentication/form";

    //系统默认的请求
    public static final String LOGIN_PAGE = "/authentication/require";

    //验证码session
    public static final String VALIDATE_IN_SESSION="VALIDATE_IMAGE_IN_SESSION";

    //此类是专门提供给controllerRequestMapping（“”）用的，因为里面只能用常量
    public static final String VALIDATE_CODE_URL= "/code";

    //短信验证码提交表单
    public static final String lOGIN_SSM_PROCESS_URL="/authentication/ssm";

    //用户登录显示表单页面
    public static final String USER_LOGIN_STATIC_PAGE = "/login-sign.html";

    //获取用户的OPENID
    public static final String GET_QQ_OPENID="https://graph.qq.com/oauth2.0/me?access_token=%S";

    //获取用户信息值
    public static final String GET_QQ_USERINFO="https://graph.qq.com/user/get_user_info?oauth_consumer_key=%S&openid=%S";

    //获取qq授权码
    public static final String GET_QQ_AUTHORIZE_CODE="https://graph.qq.com/oauth2.0/authorize";

    //获取qq的token
    public static final String GET_QQ_ACCESS_TOKEN="https://graph.qq.com/oauth2.0/token";

}
