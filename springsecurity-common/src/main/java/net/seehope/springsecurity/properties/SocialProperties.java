package net.seehope.springsecurity.properties;

import lombok.Data;

/**
 * @author JoinYang
 * @date 2022/3/15 10:29
 */
@Data
public class SocialProperties {
    private String processingUrl= "/social";
    QQProperties qq=new QQProperties();
}
