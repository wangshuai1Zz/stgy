package WebAdmin.aop;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD, ElementType.TYPE}) // 修改这里，使其可以应用于类和方法
@Retention(RetentionPolicy.RUNTIME)
public @interface AuthCheck {

    // 添加一个属性，用于指定用户类型必须为0
    boolean mustBeAdmin() default false;
}