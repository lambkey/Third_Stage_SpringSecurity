package net.seehope.springsecurity.validate;

import org.springframework.security.core.AuthenticationException;

/**
 * @author JoinYang
 * @date 2022/2/16 15:24
 */
public class ValidateCodeException extends AuthenticationException {
    public ValidateCodeException(String msg){
        super(msg);
    }
}
