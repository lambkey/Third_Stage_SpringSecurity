package net.seehope.springsecurity.validate;

import net.seehope.springsecurity.properties.ProjectProperties;
import net.seehope.springsecurity.validate.icode.SmsValidateCodeGenerator;
import org.apache.commons.lang.RandomStringUtils;

/**
 * @author JoinYang
 * @date 2022/2/17 15:13
 */


public class DefaultSmsValidateCodeGeneratorImpl implements SmsValidateCodeGenerator {

    private ProjectProperties properties;

    public void setProperties(ProjectProperties properties){
        this.properties=properties;
    }

    @Override
    public ValidateCode generate() {
        String code = RandomStringUtils.randomNumeric(properties.getSmsValidateCodeProperties().getLength());
        return new ValidateCode(code,properties.getSmsValidateCodeProperties().getExpireIn());
    }
}
