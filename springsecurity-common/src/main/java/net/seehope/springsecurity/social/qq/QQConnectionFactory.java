package net.seehope.springsecurity.social.qq;

import org.springframework.social.connect.ApiAdapter;
import org.springframework.social.connect.support.OAuth2ConnectionFactory;
import org.springframework.social.oauth2.OAuth2ServiceProvider;

/**
 * @author JoinYang
 * @date 2022/3/15 9:55
 */
public class QQConnectionFactory extends OAuth2ConnectionFactory<QQ> {

    public QQConnectionFactory(String providerId, String appid,String appSecret) {
        super(providerId, new QQServiceProvider(appid,appSecret),new QQAdapter());

    }
}
