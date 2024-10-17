package WebAdmin.service.impl;

import Common.exception.MyApiError;
import WebAdmin.config.MinioConfig;
import WebAdmin.service.FileService;
import cn.hutool.core.util.IdUtil;
import io.minio.*;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import model.enums.ViewContentTypeEnum;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Service
@Slf4j
public class FileServiceImpl implements FileService {

    @Resource
    private MinioConfig minioConfig;

    @Override
    public String add(MultipartFile file) {

        try (MinioClient minioClient = minioConfig.createMinioClient()) {
            String BacketName = minioConfig.getBatket();
            String currentDate = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
            String uuid = IdUtil.simpleUUID();
            String Filename = file.getOriginalFilename();
            String extension = Filename != null && Filename.contains(".")
                    ? Filename.substring(Filename.lastIndexOf("."))
                    : "";
            String objectName = STR."\{currentDate}/\{uuid}\{extension}";

            boolean found =
                    minioClient.bucketExists(BucketExistsArgs.builder().bucket(BacketName).build());
            if (!found) {
                minioClient.makeBucket(MakeBucketArgs.builder().bucket(BacketName).build());
            }

            InputStream bais = file.getInputStream();
            long start = System.currentTimeMillis();
            minioClient.putObject(
                    PutObjectArgs
                            .builder()
                            .bucket(BacketName)
                            .object(objectName)
                            .contentType(ViewContentTypeEnum.getContentType(objectName))
                            .stream(bais, bais.available(), -1)
                            .build());
            bais.close();
            log.info("successfully 耗时：{}", System.currentTimeMillis() - start);

            return minioConfig.getEndpoint()+"/"+minioConfig.getBatket()+"/"+objectName;
        } catch (Exception e) {
            throw new MyApiError("err",300);
        }

    }
}
