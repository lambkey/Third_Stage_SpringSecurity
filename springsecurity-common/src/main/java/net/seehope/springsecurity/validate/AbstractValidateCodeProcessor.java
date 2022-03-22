package net.seehope.springsecurity.validate;


import net.seehope.springsecurity.properties.ProjectConstant;
import net.seehope.springsecurity.validate.icode.ValidateCodeGenerator;
import net.seehope.springsecurity.validate.icode.ValidateCodeProcessor;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;

import java.io.IOException;
import java.util.List;

/**
 * @author JoinYang
 * @date 2022/3/1 15:00
 *将验证码的逻辑抽象三个步骤：创建、存储、发送
 */
public abstract class AbstractValidateCodeProcessor<C extends ValidateCode> implements ValidateCodeProcessor {
    //将验证码存在session当中
    private SessionStrategy strategy=new HttpSessionSessionStrategy();

    @Autowired
    private List<ValidateCodeGenerator> validateCodeGenerators;

    @Override
    public void create(ServletWebRequest request) throws Exception {
        C validateCode=generate(request);
        save(request,validateCode);
        send(request,validateCode);
    }

    //生成验证码
    private C generate(ServletWebRequest request){
        for (ValidateCodeGenerator validateCodeGenerator:validateCodeGenerators){
            //判断两个类是否有继承关系
            if (getGenerateClass().isAssignableFrom(validateCodeGenerator.getClass())){

                return (C)validateCodeGenerator.generate();
            }

        }
        return null;
    }

    //存储
    private void save(ServletWebRequest request,C validateCode){
        strategy.setAttribute(request, ProjectConstant.VALIDATE_IN_SESSION,validateCode);
    }

    abstract void send(ServletWebRequest request,C validateCode) throws IOException, ServletRequestBindingException;

    //获取继承类的类
    abstract Class getGenerateClass();

    @Override
    public void validate(ServletWebRequest request) throws ServletRequestBindingException {

        ValidateCode validateCode = (ValidateCode) strategy.getAttribute(request,ProjectConstant.VALIDATE_IN_SESSION);//拿到session中的验证码

        String codeInRequest = ServletRequestUtils.getStringParameter(request.getRequest(),"validateCode");//获得请求时候的输入
        //如果都为空,则需要抛异常
        if (validateCode == null){
            throw new ValidateCodeException("验证码不存在");
        }
        if (codeInRequest == null){
            throw new ValidateCodeException("请输入验证码,验证码的值为空");
        }
        if (validateCode .isExpired()){
            throw new ValidateCodeException("验证码已过期,请重新输入");
        }
        if (!StringUtils.equals(codeInRequest, validateCode.getCode())){
            throw new ValidateCodeException("验证码不匹配,请重新输入");
        }
        //不管验证有没有成功都销毁session
        strategy.removeAttribute(request,ProjectConstant.VALIDATE_IN_SESSION);
    }
}
