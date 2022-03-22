package net.seehope.springsecurity.pojo;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Column;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.util.Date;

/**
 * @author JoinYang
 * @date 2022/2/10 17:35
 */
@Data
//当实体类与其映射的数据库表名不同名时需要使用 @Table 标注说明
@Table(name = "`user`")
public class User {

    //@Column用来标注与数据库中字段对应的关系
    @Column(name = "`id`")
    private Integer id;

    //@Length表示对用户名称长度的限制
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
