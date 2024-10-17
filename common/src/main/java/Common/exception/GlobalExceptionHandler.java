package Common.exception;

import Common.result.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(MyApiError.class)
    public Result<Object> apiExceptions(MyApiError MyApiError) {
        log.warn(MyApiError.getMessage());
        return Result.fail();
    }
}
