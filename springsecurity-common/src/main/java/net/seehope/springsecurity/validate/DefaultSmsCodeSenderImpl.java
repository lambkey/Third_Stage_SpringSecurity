package net.seehope.springsecurity.validate;

import lombok.extern.slf4j.Slf4j;
import net.seehope.springsecurity.validate.icode.SmsCodeSender;

/**
 * @author JoinYang
 * @date 2022/2/17 15:11
 */
@Slf4j
public class DefaultSmsCodeSenderImpl implements SmsCodeSender {
    @Override
    public void send(String code, String mobile) {
        log.info("正在向手机号码{}发送短信,验证码为{}",mobile,code);
    }
}
