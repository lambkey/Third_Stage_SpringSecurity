package net.seehope.springsecurity.validate;

import net.seehope.springsecurity.validate.icode.ImageValidateCodeGenerator;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

import javax.imageio.ImageIO;
import java.io.IOException;

/**
 * @author JoinYang
 * @date 2022/3/1 15:03
 */

@Component
public class ImageValidateCodeProcessor extends AbstractValidateCodeProcessor<DefaultImageValidateCode>{

    @Override
    public boolean support(String type) {
        return StringUtils.equals("image",type);
    }

    @Override
    void send(ServletWebRequest request,DefaultImageValidateCode defaultImageValidateCode) throws IOException {
        System.out.println(defaultImageValidateCode.getCode());
        ImageIO.write(defaultImageValidateCode.getImage(),"JPEG",request.getResponse().getOutputStream());
    }

    @Override
    Class getGenerateClass() {
        return ImageValidateCodeGenerator.class;
    }
}
