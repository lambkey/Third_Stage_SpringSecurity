package net.seehope.springsecurity.social;

import lombok.Data;
import org.springframework.social.security.SocialAuthenticationFilter;
import org.springframework.social.security.SpringSocialConfigurer;

/**
 * @author JoinYang
 * @date 2022/3/15 11:11
 */
@Data
public class SocialAuthenticationConfig extends SpringSocialConfigurer {
    private String socialAuthenticationProcessingUrl;

    public SocialAuthenticationConfig(String socialAuthenticationProcessingUrl) {
        this.socialAuthenticationProcessingUrl=socialAuthenticationProcessingUrl;
    }

    @Override
    protected <T> T postProcess(T object) {

        SocialAuthenticationFilter t = (SocialAuthenticationFilter) super.postProcess(object);
        t.setFilterProcessesUrl(socialAuthenticationProcessingUrl);
        return (T) t;
    }
}
