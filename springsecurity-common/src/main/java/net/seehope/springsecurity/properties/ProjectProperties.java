package net.seehope.springsecurity.properties;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author JoinYang
 * @date 2022/2/15 13:56
 */
@Configuration
@ConfigurationProperties(prefix = "net.seehope")

@Data
public class ProjectProperties {
    private WebProperties webProperties=new WebProperties();
    private ImageValidateCodeProperties imageValidateCodeProperties =new ImageValidateCodeProperties();
    private SmsValidateCodeProperties smsValidateCodeProperties=new SmsValidateCodeProperties();
    private SocialProperties social=new SocialProperties();
}
