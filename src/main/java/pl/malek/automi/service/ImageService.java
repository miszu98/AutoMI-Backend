package pl.malek.automi.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ImageService {
    String upload(MultipartFile file) throws IOException;
}
