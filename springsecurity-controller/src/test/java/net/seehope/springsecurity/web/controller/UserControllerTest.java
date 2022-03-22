package net.seehope.springsecurity.web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.seehope.springsecurity.SecurityApplication;
import net.seehope.springsecurity.pojo.User;
import net.seehope.springsecurity.pojo.vo.UserVo;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.ServletContext;

/**
 * @author JoinYang
 * @date 2022/2/10 12:43
 */
@SpringBootTest(classes = SecurityApplication.class)
@RunWith(SpringRunner.class)
public class UserControllerTest {
    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    public ObjectMapper objectMapper;

    //模拟MVC环境相关
    private MockMvc mockMvc;

    @Before
    public void setUp(){
        //每次方法执行之前都模拟一个mvc环境
        //每次运行都相当于服务器的重启
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void whenQueryUserSuccess() throws Exception{
        //为json传参做准备
        //模拟发送一个get的请求
                             String result=mockMvc.perform(MockMvcRequestBuilders.get("/user")
                             //.param("username", user.getUsername())
/*设置访问方式是JSON形式字符串,但是已经过期*/
                             .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
/*设置另外的jason传参格式*/
                             //.content(objectMapper.writeValueAsString(user))
                        )

/*期望得到的状态码是200*/

                            .andExpect(MockMvcResultMatchers.status().isOk())
/*期望得到的是传入对象的个数，如果注释会输出具体内容*/
                                //.andExpect(MockMvcResultMatchers.jsonPath("$.data.length()").value(200))
                            .andReturn()//对响应的期待设置完毕
                            .getResponse()//获取响应头
                            .getContentAsString();//将响应头转化为String
                            System.out.println(result);//输出响应中的json数据
    }
    @Test
    public void whenInsertUserSuccess() throws Exception{
        //为json传参做准备
        UserVo user = new UserVo();
        user.setUsername("杨可爱1");
        //模拟发送一个get的请求
        String result=mockMvc.perform(MockMvcRequestBuilders.post("/user")

/*设置访问方式是JSON形式字符串,但是已经过期*/
                        .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
/*设置另外的jason传参格式*/
                        .content(objectMapper.writeValueAsString(user))
        )

/*期望得到的状态码是200*/

                .andExpect(MockMvcResultMatchers.status().isOk())
/*期望得到的是传入对象的个数，如果注释会输出具体内容*/
                .andReturn()//对响应的期待设置完毕
                .getResponse()//获取响应头
                .getContentAsString();//将响应头转化为String
        System.out.println(result);//输出响应中的json数据
    }
}
