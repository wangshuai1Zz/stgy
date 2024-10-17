package WebAdmin.config;

import WebAdmin.security.JwtInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import Common.utils.StringToBaseEnum;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    private final StringToBaseEnum stringToBaseEnum;

    public WebConfig(StringToBaseEnum stringToBaseEnum) {
        this.stringToBaseEnum = stringToBaseEnum;
    }

    @Value("${mvc_conf.addPathPatterns}")
    private String addPathPatterns;

    @Value("${mvc_conf.excludePathPatterns}")
    private String excludePathPatterns;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new JwtInterceptor())
                .addPathPatterns(addPathPatterns)
                .excludePathPatterns(excludePathPatterns.split(",\\s*"));
    }

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverterFactory(stringToBaseEnum);
    }

}