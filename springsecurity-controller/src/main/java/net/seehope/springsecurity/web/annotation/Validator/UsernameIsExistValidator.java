package net.seehope.springsecurity.web.annotation.Validator;

import lombok.AllArgsConstructor;
import net.seehope.springsecurity.mapper.UserMapper;
import net.seehope.springsecurity.pojo.User;
import net.seehope.springsecurity.web.annotation.UserIsExistOrNoExistAnnotation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

/**
 * @author JoinYang
 * @date 2022/2/11 15:28
 */
@AllArgsConstructor
public class UsernameIsExistValidator implements ConstraintValidator<UserIsExistOrNoExistAnnotation,Object> {

    private UserMapper userMapper;

    @Override
    public void initialize(UserIsExistOrNoExistAnnotation userIsExistOrNoExist) {

    }

    @Override
    public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {
        //这里的o是前端所传进来的
        String username = (String) o;
        User user =new User();
        user.setUsername(username);
        List<User> select = userMapper.select(user);
        System.out.println("isValid:" +select.size());
        return select.size()<=0;
    }
}
