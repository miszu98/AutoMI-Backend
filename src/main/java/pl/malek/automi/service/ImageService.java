package pl.malek.automi.service;

import org.springframework.web.multipart.MultipartFile;
import pl.malek.automi.entity.ImageEntity;
import pl.malek.automi.exception.CarOfferNotFoundException;

import java.io.IOException;
import java.util.List;

public interface ImageService {
    List<String> upload(MultipartFile[] files, Long id) throws IOException, CarOfferNotFoundException;
    List<String> upload(List<String> files, Long id) throws CarOfferNotFoundException;
    void deleteImagesByOfferId(Long id);
}
