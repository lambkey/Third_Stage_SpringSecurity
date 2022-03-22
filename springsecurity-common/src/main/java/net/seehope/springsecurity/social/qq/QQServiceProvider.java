package net.seehope.springsecurity.social.qq;

import net.seehope.springsecurity.properties.ProjectConstant;
import org.springframework.social.oauth2.AbstractOAuth2ServiceProvider;
import org.springframework.social.oauth2.OAuth2Operations;

/**
 * @author JoinYang
 * @date 2022/3/14 21:21
 */
public class QQServiceProvider extends AbstractOAuth2ServiceProvider <QQ>{

    private String appid;

    public QQServiceProvider(String appid,String appsecret){
        super(new QQOauth2Template(appid,appsecret, ProjectConstant.GET_QQ_AUTHORIZE_CODE,ProjectConstant.GET_QQ_ACCESS_TOKEN));
        this.appid=appid;
    }

    @Override
    public QQ getApi(String accessToken) {
        return new QQImpl(accessToken,appid);
    }
}
