package net.seehope.springsecurity.social.qq;

import lombok.extern.slf4j.Slf4j;

import net.seehope.springsecurity.properties.ProjectConstant;
import org.apache.commons.lang.StringUtils;
import org.springframework.social.oauth2.AbstractOAuth2ApiBinding;
import org.springframework.social.oauth2.TokenStrategy;

/**
 *
 * 获取到了token之后,换取服务器提供商的用户资源的实现
 * @author JoinYang
 * @date 2022/3/14 19:59
 */
@Slf4j
public class QQImpl extends AbstractOAuth2ApiBinding implements QQ {

    private String appid;
    private String openid;

    public QQImpl(String accessToken,String appid){
        //设置当前类在使用restTemplate发送请求的时候，所有的请求参数中，都携带有accesstoken
        super(accessToken, TokenStrategy.ACCESS_TOKEN_PARAMETER);
        this.appid=appid;

        //把accessToken传到String GET_QQ_OPENID="https://graph.qq.com/oauth2.0/me?access_token=%S"的%S里面去
        String url=String.format(ProjectConstant.GET_QQ_OPENID,accessToken);

        QQOpenIdResult result=getRestTemplate().getForObject(url,QQOpenIdResult.class);

        log.info("get qq openid "+result.getOpenid());

        this.openid = result.getOpenid();
    }


    @Override
    public QQUserInfo getUserInfo() {
        String url=String.format(ProjectConstant.GET_QQ_USERINFO,appid,openid);
        QQUserInfo qqUserInfo=getRestTemplate().getForObject(url,QQUserInfo.class);
        return qqUserInfo;
    }
}
