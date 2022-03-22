package net.seehope.springsecurity.validate;

import net.seehope.springsecurity.properties.ProjectProperties;
import net.seehope.springsecurity.validate.icode.ImageValidateCodeGenerator;
import net.seehope.springsecurity.validate.icode.SmsCodeSender;
import net.seehope.springsecurity.validate.icode.SmsValidateCodeGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author JoinYang
 * @date 2022/2/17 0:14
 */
@Configuration
public class ValidateCodeConfiguration {

    @Autowired
    private ProjectProperties properties;

    @Bean
    @ConditionalOnMissingBean(value = ImageValidateCodeGenerator.class)//条件装配，找这个容器中的类和子类，如果有就不注册,没有就注册
    public ImageValidateCodeGenerator imageCodeGenerator(){
        DefaultImageValidateCodeGeneratorImpl imageCodeGenerator = new DefaultImageValidateCodeGeneratorImpl();
        imageCodeGenerator.setProperties(properties);
        return imageCodeGenerator;
    }

    @Bean
    @ConditionalOnMissingBean(value = SmsValidateCodeGenerator.class)
    public SmsValidateCodeGenerator smsValidateCodeGenerator(){
        DefaultSmsValidateCodeGeneratorImpl defaultSmsValidateCodeGenerator=new DefaultSmsValidateCodeGeneratorImpl();
        defaultSmsValidateCodeGenerator.setProperties(properties);
        return defaultSmsValidateCodeGenerator;
    }

    @Bean
    @ConditionalOnMissingBean(value = SmsCodeSender.class)
    public SmsCodeSender smsCodeSender(){
        return new DefaultSmsCodeSenderImpl();
    }
}
