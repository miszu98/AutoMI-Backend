package pl.malek.automi.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pl.malek.automi.dto.Urls;
import pl.malek.automi.exception.CarOfferNotFoundException;
import pl.malek.automi.service.ImageService;

import java.io.IOException;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/image-upload")
public class ImageUploaderController {

    private final ImageService imageService;

    @PostMapping("/")
    public ResponseEntity<List<String>> upload(Long offerId, @RequestBody MultipartFile... files) throws IOException, CarOfferNotFoundException {
        return ResponseEntity.status(HttpStatus.OK).body(imageService.upload(files, offerId));
    }

    @PostMapping("/s/")
    public ResponseEntity<List<String>> upload(@RequestBody Urls urls) throws IOException, CarOfferNotFoundException {
        return ResponseEntity.status(HttpStatus.OK).body(imageService.upload(urls.getLinks(), urls.getOfferId()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> upload(@PathVariable Long id){
        imageService.deleteImagesByOfferId(id);
        return ResponseEntity.status(HttpStatus.OK).body("delete");
    }
}
