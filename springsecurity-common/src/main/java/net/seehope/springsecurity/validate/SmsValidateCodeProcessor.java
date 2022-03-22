package net.seehope.springsecurity.validate;

import net.seehope.springsecurity.validate.icode.SmsCodeSender;
import net.seehope.springsecurity.validate.icode.SmsValidateCodeGenerator;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * @author JoinYang
 * @date 2022/3/1 15:04
 */
@Component
public class SmsValidateCodeProcessor extends AbstractValidateCodeProcessor<ValidateCode>{

    @Autowired
    private SmsCodeSender smsCodeSender;

    @Override
    public boolean support(String type) {
        return StringUtils.equals("ssm",type);
    }

    @Override
    void send(ServletWebRequest servletWebRequest,ValidateCode validateCode) throws ServletRequestBindingException {
        smsCodeSender.send(validateCode.getCode(), ServletRequestUtils.getRequiredStringParameter(servletWebRequest.getRequest(),"mobile"));
    }

    @Override
    Class getGenerateClass() {
        return SmsValidateCodeGenerator.class;
    }
}
