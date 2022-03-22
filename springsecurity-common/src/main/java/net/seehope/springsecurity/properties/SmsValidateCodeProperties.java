package net.seehope.springsecurity.properties;

import lombok.Data;

/**
 * @author JoinYang
 * @date 2022/2/17 15:21
 */
@Data
public class SmsValidateCodeProperties {
    private int length = 4;
    private int expireIn = 60 * 15;
}
