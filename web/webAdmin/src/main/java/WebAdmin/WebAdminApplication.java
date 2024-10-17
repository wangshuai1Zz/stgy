package WebAdmin;

import Common.config.RedisConfig;
import Common.exception.GlobalExceptionHandler;
import Common.service.impl.RedisServiceImpl;
import Common.utils.StringToBaseEnum;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@MapperScan("WebAdmin.mapper")
@Import({StringToBaseEnum.class, GlobalExceptionHandler.class, RedisConfig.class, RedisServiceImpl.class})
@EnableScheduling
public class WebAdminApplication {
    public static void main(String[] args) {
        SpringApplication.run(WebAdminApplication.class, args);
    }
}