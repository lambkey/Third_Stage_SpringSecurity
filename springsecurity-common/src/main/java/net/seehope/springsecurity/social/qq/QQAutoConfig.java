package net.seehope.springsecurity.social.qq;

import lombok.AllArgsConstructor;
import net.seehope.springsecurity.properties.ProjectProperties;
import net.seehope.springsecurity.properties.QQProperties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.social.SocialAutoConfigurerAdapter;
import org.springframework.context.annotation.Configuration;
import org.springframework.social.connect.ConnectionFactory;

/**
 * @author JoinYang
 * @date 2022/3/15 9:59
 */
@Configuration
//检测yml中有没有appid这个字段，配置了这个字段，这个才会自动装配上去
@ConditionalOnProperty(prefix = "net.seehope.social.qq",name = "appid")
@AllArgsConstructor
public class QQAutoConfig extends SocialAutoConfigurerAdapter {

    private ProjectProperties properties;

    @Override
    protected ConnectionFactory<?> createConnectionFactory() {
        QQProperties qq = properties.getSocial().getQq();
        return new QQConnectionFactory(qq.getProviderId(), qq.getAppid(), qq.getAppsecret());
    }
}
