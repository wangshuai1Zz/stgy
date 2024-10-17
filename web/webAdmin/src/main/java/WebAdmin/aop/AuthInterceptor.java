package WebAdmin.aop;

import Common.exception.MyApiError;
import Common.utils.ThreadUtils;
import WebAdmin.service.SystemUserService;
import jakarta.annotation.Resource;
import model.entity.SystemUser;
import model.enums.SystemUserType;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * 检查权限
 */
@Aspect
@Component
public class AuthInterceptor {

    @Resource
    private final SystemUserService systemUserService;

    public AuthInterceptor(SystemUserService systemUserService) {
        this.systemUserService = systemUserService;
    }

    // 定义切点，拦截带有AuthCheck注解的方法和类
    @Pointcut("@within(AuthCheck) || @annotation(AuthCheck)")
    public void authCheckPointcut() {
    }

    /**
     * 执行拦截
     *
     * @param joinPoint
     * @return
     */
    @Around("authCheckPointcut()")
    public Object doInterceptor(ProceedingJoinPoint joinPoint) throws Throwable {
        // 获取方法上的注解
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        AuthCheck methodAuthCheck = method.getAnnotation(AuthCheck.class);

        // 获取类上的注解
        AuthCheck classAuthCheck = joinPoint.getTarget().getClass().getAnnotation(AuthCheck.class);

        // 当前登录用户
        Long local = ThreadUtils.getLocal();
        SystemUser user = systemUserService.getById(local);
        SystemUserType type = user.getType();

        // 检查方法注解和类注解
        if ((methodAuthCheck != null && methodAuthCheck.mustBeAdmin()) ||
                (classAuthCheck != null && classAuthCheck.mustBeAdmin())) {
            if (type.getCode() != 0) { //
                throw new MyApiError("权限不足，用户类型必须为admin",200);
            }
        }

        return joinPoint.proceed();
    }
}
