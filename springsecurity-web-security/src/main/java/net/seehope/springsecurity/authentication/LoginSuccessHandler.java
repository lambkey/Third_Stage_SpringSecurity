package net.seehope.springsecurity.authentication;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.seehope.springsecurity.enums.LoginType;
import net.seehope.springsecurity.properties.ProjectProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author JoinYang
 * @date 2022/2/16 10:33
 */
@Component
public class LoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    @Autowired
    private ProjectProperties properties;

    @Autowired
    private ObjectMapper objectMapper;

    //登录成功处理
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws ServletException, IOException {
        ////这个是由自己来控制要给用户返回的东西，是JSON还是html形式，由自己决定,可以在配置类里面配置
        if (LoginType.JSON.equals(properties.getWebProperties().getLoginType())){
            response.setContentType("application/json;charset=utf-8");
            //如果验证成功,则把票据传回到前端
            response.getWriter().write(objectMapper.writeValueAsString(authentication));
        }else
        {
            super.onAuthenticationSuccess(request, response, authentication);
        }
    }
}
