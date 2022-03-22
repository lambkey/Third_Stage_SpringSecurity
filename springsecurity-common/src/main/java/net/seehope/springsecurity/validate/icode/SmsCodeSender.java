package net.seehope.springsecurity.validate.icode;

/**
 * @author JoinYang
 * @date 2022/2/17 15:01
 */
public interface SmsCodeSender {
    void send(String code,String mobile);
}
