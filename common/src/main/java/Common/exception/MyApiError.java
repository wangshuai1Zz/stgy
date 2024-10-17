package Common.exception;

import Common.result.ResultCodeEnum;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MyApiError extends RuntimeException {
    private final int code;
    private final String message;


    public MyApiError(String message, int code) {
        this.code = code;
        this.message = message;
    }

    public static MyApiError Fail(ResultCodeEnum resultCodeEnum){
        return new MyApiError(resultCodeEnum.getMessage(), resultCodeEnum.getCode());
    }

}
