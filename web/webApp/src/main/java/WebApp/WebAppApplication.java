package WebApp;

import Common.config.RedisConfig;
import Common.exception.GlobalExceptionHandler;
import Common.service.impl.RedisServiceImpl;
import Common.utils.StringToBaseEnum;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import({StringToBaseEnum.class, GlobalExceptionHandler.class, RedisConfig.class, RedisServiceImpl.class})
public class WebAppApplication {
    public static void main(String[] args) {
        SpringApplication.run(WebAppApplication.class, args);
    }
}