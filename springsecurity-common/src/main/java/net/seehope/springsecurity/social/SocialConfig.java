package net.seehope.springsecurity.social;

import lombok.AllArgsConstructor;
import net.seehope.springsecurity.properties.ProjectProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.social.config.annotation.EnableSocial;
import org.springframework.social.config.annotation.SocialConfigurerAdapter;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.connect.jdbc.JdbcUsersConnectionRepository;
import org.springframework.social.security.SpringSocialConfigurer;

import javax.sql.DataSource;

/**
 * @author JoinYang
 * @date 2022/3/15 10:47
 */
@Configuration
@EnableSocial
@AllArgsConstructor
public class SocialConfig extends SocialConfigurerAdapter {

    private DataSource dataSource;
    private ProjectProperties projectProperties;

    @Override
    public UsersConnectionRepository getUsersConnectionRepository(ConnectionFactoryLocator connectionFactoryLocator) {

        JdbcUsersConnectionRepository jdbcUsersConnectionRepository=new JdbcUsersConnectionRepository(dataSource, connectionFactoryLocator, Encryptors.noOpText());


        return jdbcUsersConnectionRepository;
    }
    @Bean
    public SpringSocialConfigurer socialAuthenticationConfig() {
        SocialAuthenticationConfig configurer = new SocialAuthenticationConfig(projectProperties.getSocial().getProcessingUrl());
        return configurer;
    }
}
