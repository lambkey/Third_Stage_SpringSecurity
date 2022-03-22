package net.seehope.springsecurity.pojo.vo;

import lombok.Data;
import net.seehope.springsecurity.web.annotation.UserIsExistOrNoExistAnnotation;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Column;
import javax.validation.constraints.Past;
import java.util.Date;

/**
 * @author JoinYang
 * @date 2022/2/12 17:12
 */
@Data
public class UserVo {

        //@Column用来标注与数据库中字段对应的关系
        @Column(name = "`id`")
        private Integer id;

        //@Length表示对用户名称长度的限制
        //@UserIsExistOrNoExistAnnotation为自定义的验证查询数据库是否有重复的用户名
        @UserIsExistOrNoExistAnnotation(message = "用户名已经存在,请重新插入")
        @Column(name = "`username`")
        @Length(min = 2,max = 20,message = "请输入2-20位之间的用户名")
        private  String username;

        //@Past设定传入的日期一定要是过去的
        @Column(name = "`birthday`")
        @Past

        private Date birthday;

        @Column(name = "`sex`")

        private String sex;

        @Column(name = "`address`")
        private String address;

        @Column(name = "`password`")
        private String password;

        @Column(name = "`mobile`")
        private String mobile;

        private static final long seraVersionUID = 1L;
}
