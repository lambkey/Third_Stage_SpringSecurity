package net.seehope.springsecurity.validate;

import net.seehope.springsecurity.properties.ProjectConstant;
import net.seehope.springsecurity.validate.icode.ValidateCodeProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author JoinYang
 * @date 2022/2/16 12:06
 * 生成
 * 存储
 * 写出
 */
@Controller
@RequestMapping(ProjectConstant.VALIDATE_CODE_URL)
public class ValidateCodeController {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private HttpServletResponse response;

    //会自动找到spring容器中的bean容器中的ValidateCodeProcessor的子实现
    @Autowired
    private List<ValidateCodeProcessor>validateCodeProcessors;
    /*
    *
    * 通过判断type是什么，调用不同的processor
    * 如果添加了新的实现，那么名字一定要叫xxxValidateCodeProcessor
    *
    * */
    @GetMapping("/{type}")
    public void createValidateCode(@PathVariable String type) throws Exception {
        for (ValidateCodeProcessor validateCodeProcessor:validateCodeProcessors){
            if (validateCodeProcessor.support(type)){
                validateCodeProcessor.create(new ServletWebRequest(request,response));
            }
        }
    }

}
