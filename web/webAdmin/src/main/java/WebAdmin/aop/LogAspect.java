package WebAdmin.aop;

import cn.hutool.core.date.StopWatch;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;

/**
 * 日志切面
 * 用于记录 controller 层的日志
 */

@Aspect
@Component
@Slf4j
public class LogAspect {
    private final ThreadLocal<StopWatch> startTimeThreadLocal = new ThreadLocal<>();

    @Pointcut("execution(* WebAdmin.controller..*.*(..))")
    public void controllerLog() {
    }

    // 在方法执行之前执行
    @Before("controllerLog()")
    public void beforeController(JoinPoint joinPoint) {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start(); // 开始计时
        // 将 StopWatch 存储到当前线程变量中，以便 afterController 方法能够访问
        startTimeThreadLocal.set(stopWatch);
    }

    // 在方法正常返回之后执行
    @AfterReturning(pointcut = "controllerLog()", returning = "result")
    public void afterController(JoinPoint joinPoint, Object result) {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        String methodName = methodSignature.getMethod().getName();
        String results = result == null ? "null" : result.toString();
        if (results.length() > 100) {
            results = STR."\{results.substring(0, 100)}...";
        }
        StopWatch stopWatch = startTimeThreadLocal.get();
        stopWatch.stop(); // 停止计时
        long duration = stopWatch.getTotalTimeMillis(); // 获取方法执行时间
        startTimeThreadLocal.remove(); // 清除当前线程变量
        // 构建输出参数的日志信息
        log.info("响应方法: {}, 响应结果: {}, 响应时间: {}ms", methodName, results, duration);
    }
}
