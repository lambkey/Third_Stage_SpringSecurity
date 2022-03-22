package net.seehope.springsecurity.validate;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.awt.image.BufferedImage;

/**
 * @author JoinYang
 * @date 2022/2/16 12:02
 */
@Data
@NoArgsConstructor
public class DefaultImageValidateCode extends ValidateCode {
    private BufferedImage image;

    public  DefaultImageValidateCode(String code, int expireTime, BufferedImage image) {
        super(code, expireTime);
        this.image=image;
    }
}
