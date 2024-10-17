package WebAdmin.service;

import org.springframework.web.multipart.MultipartFile;

public interface FileService {
    String add(MultipartFile file);

}
