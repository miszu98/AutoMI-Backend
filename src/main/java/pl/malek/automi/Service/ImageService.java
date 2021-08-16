package pl.malek.automi.Service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ImageService {
    String upload(MultipartFile file) throws IOException;
}
