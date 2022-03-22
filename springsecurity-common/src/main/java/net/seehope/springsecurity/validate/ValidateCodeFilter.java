package net.seehope.springsecurity.validate;

import lombok.Data;

import net.seehope.springsecurity.properties.ProjectConstant;
import net.seehope.springsecurity.properties.ProjectProperties;
import net.seehope.springsecurity.validate.icode.ValidateCodeProcessor;
import org.apache.commons.lang.StringUtils;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author JoinYang
 * @date 2022/2/16 14:45
 * 验证码逻辑类，在用户访问之前验证验证码的正确与否
 */
//继承OncePerRequestFilter这个类表示用户第一次请求会调用这个类
@Data
public class ValidateCodeFilter extends OncePerRequestFilter {
    //填装请求域名
    private Set<String> urls=new HashSet<String>();

    private AntPathMatcher antPathMatcher=new AntPathMatcher();

    //通过get,set用得时候才注进来
    private ProjectProperties properties;

    private SessionStrategy strategy = new HttpSessionSessionStrategy();

    //通过get,set用得时候才注进来
    private AuthenticationFailureHandler authenticationFailureHandler;


    private Map<String,ValidateCodeProcessor> validateCodeProcessors;

    @Override
    public void afterPropertiesSet() throws ServletException {
        super.afterPropertiesSet();
        //获取所有请求地址,以逗号隔开,统一进行验证码过滤（设计验证码）
        String[] urlConfig=StringUtils.splitByWholeSeparatorPreserveAllTokens(properties.getImageValidateCodeProperties().getValidateUrl(),",");
        for (String url:urlConfig){
            urls.add(url);
        }
        //添加默认的登录页面
        urls.add(ProjectConstant.IMAGE_PROCESSING_URL);
        urls.add(ProjectConstant.lOGIN_SSM_PROCESS_URL);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {

        boolean flag=false;

        for (String url:urls){
            if (antPathMatcher.match(url,httpServletRequest.getRequestURI())){
                flag=true;
            }
        }


        //System.out.println(httpServletRequest.getRequestURI());

        String action =httpServletRequest.getRequestURI();

        String validateType="";

        switch(action){
            case "/authentication/form":{
                validateType="image"+"ValidateCodeProcessor";
            }
            break;

            case "/authentication/ssm":{
                validateType="sms"+"ValidateCodeProcessor";
            }
            default:
                break;
        }

        if (flag){
           try {

               //验证码验证
               validateCodeProcessors.get(validateType).validate(new ServletWebRequest(httpServletRequest,httpServletResponse));
               //validate(new ServletWebRequest(httpServletRequest, httpServletResponse));
               filterChain.doFilter(httpServletRequest,httpServletResponse);
           }catch (AuthenticationException e){
               //如果验证失败,调用验证失败处理器
               authenticationFailureHandler.onAuthenticationFailure(httpServletRequest,httpServletResponse,e);
           }
        }else {
            filterChain.doFilter(httpServletRequest,httpServletResponse);
        }
    }


    public Map<String, ValidateCodeProcessor> getValidateCodeProcessors() {
        return validateCodeProcessors;
    }

    public void setValidateCodeProcessors(Map<String, ValidateCodeProcessor> validateCodeProcessors) {
        this.validateCodeProcessors = validateCodeProcessors;
    }
}
