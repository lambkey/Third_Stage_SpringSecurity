package net.seehope.springsecurity.authentication;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.seehope.springsecurity.enums.LoginType;
import net.seehope.springsecurity.properties.ProjectProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author JoinYang
 * @date 2022/3/12 13:38
 */
@Component
public class AuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {

    @Autowired
    private ProjectProperties properties;

    @Autowired
    private ObjectMapper objectMapper;


    //登录失败处理
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        //这个是由自己来控制要给用户返回的东西，是JSON还是html形式，由自己决定，可以在配置类里面配置
        if(LoginType.JSON.equals(properties.getWebProperties().getLoginType())){
            exception.printStackTrace();//在控制台输出异常
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().write(objectMapper.writeValueAsString(exception));
        }else {
            super.onAuthenticationFailure(request, response, exception);
        }

    }
}

