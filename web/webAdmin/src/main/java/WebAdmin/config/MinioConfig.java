package WebAdmin.config;

import io.minio.MinioClient;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;

@SpringBootConfiguration
@Getter
public class MinioConfig {
    @Value("${minio.endpoint}")
    private String endpoint;

    @Value("${minio.AccessKey}")
    private String AccessKey;

    @Value("${minio.SecretKey}")
    private String SecretKey;

    @Value("${minio.Batket}")
    private String Batket;

    @Bean
    public MinioClient createMinioClient(){
        return MinioClient.builder()
                .endpoint(endpoint)
                .credentials(AccessKey, SecretKey)
                .build();
    }


}
