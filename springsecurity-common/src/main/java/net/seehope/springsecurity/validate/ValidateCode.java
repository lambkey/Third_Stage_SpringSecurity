package net.seehope.springsecurity.validate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @author JoinYang
 * @date 2022/2/16 11:42
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
//验证码
public class ValidateCode {

    /*
    * 验证码的值
    * */
    private String code;

    /*
    * 验证码有效时间
    * */
    private LocalDateTime expireTime;

    /*
    * expireTime int 单位是s expireTime秒之后验证码失效
    * */

    public ValidateCode(String code, int expireTime) {
        //往expireTime里面添加时间
        this.code = code;
        this.expireTime =LocalDateTime.now().plusSeconds(expireTime);
    }

    public boolean isExpired(){
        //返回一个在expireTime之后失效
        return LocalDateTime.now().isAfter(this.expireTime);
    }
}
