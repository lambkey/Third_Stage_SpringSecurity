package net.seehope.springsecurity.authentication.ssm;

import net.seehope.springsecurity.properties.ProjectConstant;
import net.seehope.springsecurity.properties.ProjectProperties;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.util.Assert;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author JoinYang
 * @date 2022/3/3 10:09
 * 短信过滤器
 */
//参考UsernamePasswordAuthenticationFilter的源码
//第一步，获取用户的手机号
public class SsmValidateCodeAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

    private static ProjectProperties projectProperties;

    //这个是用户在前端输入手机号的id
    public static final String SPRING_SECURITY_FORM_MOBILE_KEY = "phone";

    private String usernameParameter = SPRING_SECURITY_FORM_MOBILE_KEY;

    private boolean postOnly = true;

    public SsmValidateCodeAuthenticationFilter() {

        //填写短信验证码的提交处理路径，前端用户手机号提交的表单
        super(new AntPathRequestMatcher(ProjectConstant.lOGIN_SSM_PROCESS_URL, "POST"));
    }

    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        if (this.postOnly && !request.getMethod().equals("POST")) {
            throw new AuthenticationServiceException("Authentication method not supported: " + request.getMethod());
        } else {
            String username = this.obtainUsername(request);

            if (username == null) {
                username = "";
            }



            username = username.trim();
            //1、这里的token原来是UsernamePasswordAuthenticationToken，现在经行了替换
            //2、这个token是用户进行点击登录，此过滤器会对登录进行拦截，把手机号拦截封装成一个未认证的token
            //3、这个token最终会传给AuthenticationManager（整个系统只存在唯一一个AuthenticationManager）
                //它会检索系统中所有的Provider
            //把用户名（手机号传入token）
            SsmValidateCodeAuthenticationToken authRequest = new SsmValidateCodeAuthenticationToken(username);

            //把请求信息设置到token中
            this.setDetails(request, authRequest);
            return this.getAuthenticationManager().authenticate(authRequest);
        }
    }



    protected String obtainUsername(HttpServletRequest request) {
        return request.getParameter(this.usernameParameter);
    }

    protected void setDetails(HttpServletRequest request, SsmValidateCodeAuthenticationToken authRequest) {
        authRequest.setDetails(this.authenticationDetailsSource.buildDetails(request));
    }

    public void setUsernameParameter(String usernameParameter) {
        Assert.hasText(usernameParameter, "Username parameter must not be empty or null");
        this.usernameParameter = usernameParameter;
    }

    public ProjectProperties getProjectProperties() {
        return projectProperties;
    }

    public void setProjectProperties(ProjectProperties projectProperties) {
        this.projectProperties = projectProperties;
    }

    public void setPostOnly(boolean postOnly) {
        this.postOnly = postOnly;
    }

    public final String getUsernameParameter() {
        return this.usernameParameter;
    }}


