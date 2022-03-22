package net.seehope.springsecurity.authentication;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.codec.Base64;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.*;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.security.sasl.AuthenticationException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author JoinYang
 * @date 2022/3/12 11:04
 * 1、要求登录请求除了包含用户名和密码之外，
 * 2、还需要在authorization请求携带注册了的clientid和clientsecret
 */
@Configuration
@AllArgsConstructor
public class AuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    private ClientDetailsService clientDetailsService;
    private AuthorizationServerTokenServices authorizationServerTokenServices;
    private ObjectMapper objectMapper;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws ServletException, IOException {
        String header = request.getHeader("Authorization");
        System.out.println(header);
        if (header==null||!header.startsWith("Basic")){
            throw new AuthenticationException("请求头中未包含client的信息");
        }
        String[] parms = extractAndDecodeHeader(header);
        String clientId =parms[0];
        String clientSecret =parms[1];

        ClientDetails clientDetails =clientDetailsService.loadClientByClientId(clientId);
        if (clientDetails==null){
            throw new AuthenticationException("传入的Client信息没有注册:"+clientId);
        }
        if (!StringUtils.equals(clientSecret,clientDetails.getClientSecret())){
            throw new AuthenticationException("Clientid或者clientSecret错误:"+clientSecret);
        }

        //custom表示自定义的
        TokenRequest tokenRequest = new TokenRequest(MapUtils.EMPTY_MAP,clientId,clientDetails.getScope(),"custom");
        OAuth2Request oAuth2Request = tokenRequest.createOAuth2Request(clientDetails);
        OAuth2Authentication auth2Authentication=new OAuth2Authentication(oAuth2Request, authentication);
        OAuth2AccessToken auth2AccessToken =authorizationServerTokenServices.createAccessToken(auth2Authentication);

        response.setContentType("application/json; charset=UTF-8");
        response.getWriter().write(objectMapper.writeValueAsString(auth2AccessToken));
    }

    //解码方法
    private String[] extractAndDecodeHeader(String header) throws IOException {
        byte[] base64Token = header.substring(6).getBytes("UTF-8");

        byte[] decoded;
        try {
            decoded = Base64.decode(base64Token);
        } catch (IllegalArgumentException var7) {
            throw new BadCredentialsException("Failed to decode basic authentication token");
        }

        String token = new String(decoded, "UTF-8");
        int delim = token.indexOf(":");
        if (delim == -1) {
            throw new BadCredentialsException("Invalid basic authentication token");
        } else {
            return new String[]{token.substring(0, delim), token.substring(delim + 1)};
        }
    }
}
