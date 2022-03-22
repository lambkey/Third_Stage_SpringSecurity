package net.seehope.springsecurity.configuration;

import lombok.extern.slf4j.Slf4j;
import net.seehope.springsecurity.properties.ProjectConstant;
import net.seehope.springsecurity.properties.ProjectProperties;
import net.seehope.springsecurity.util.JSONResult;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author JoinYang
 * @date 2022/2/16 1:11
 */
@Controller
@Slf4j
public class WebLoginController {

    //此工具类能够获取用户对系统的第一次请求
    private RequestCache requestCache = new HttpSessionRequestCache();

    //此工具类的功能主要对符合的路径进行转发
    private RedirectStrategy redirectStrategy=new DefaultRedirectStrategy();

    //这里的常量会覆盖yml里设置的路径
    @RequestMapping(ProjectConstant.LOGIN_PAGE)
    //发送的请求需要有通过 HTTP 认证的认证信息
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ResponseBody
    public JSONResult requireAuthentication(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //可以得到用户请求的地址

        SavedRequest savedRequest = requestCache.getRequest(request,response);
        System.out.println(savedRequest);
        if (savedRequest!=null) {
            //获取用户的第一次访问路径,引导用户访问
            String target = savedRequest.getRedirectUrl();
            log.info("引发跳转的请求为{}", target);
            //如果用户访问以路径.html结尾,则引导到登录页面,否则返回错误界面
            if (StringUtils.endsWith(target, ".html")) {
                redirectStrategy.sendRedirect(request, response, ProjectConstant.USER_LOGIN_STATIC_PAGE);
            }

        }
        return JSONResult.errorAuthorized("访问的服务需要身份验证,请引导用户到登录页面");
    }
}
