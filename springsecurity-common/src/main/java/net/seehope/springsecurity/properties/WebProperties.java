package net.seehope.springsecurity.properties;

import lombok.Data;
import net.seehope.springsecurity.enums.LoginType;

/**
 * @author JoinYang
 * @date 2022/2/15 13:57
 */
@Data
public class WebProperties {


    private LoginType loginType = LoginType.JSON;
    private int tokenValiditySeconds=60*60*24*7;
}
