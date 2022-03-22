package net.seehope.springsecurity.web.annotation;

import net.seehope.springsecurity.web.annotation.Validator.UsernameIsExistValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author JoinYang
 * @date 2022/2/11 15:00
 */
//@Target注解可注释的位置
@Target({ElementType.FIELD,ElementType.METHOD})
//意味着，编译器会将该 Annotation 信息保留在.class 文件中，并且能被虚拟机读取,可有可无
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UsernameIsExistValidator.class)
public @interface UserIsExistOrNoExistAnnotation {
    String message() default "username is exist";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
