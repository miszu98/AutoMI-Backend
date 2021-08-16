package pl.malek.automi.Controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import pl.malek.automi.Service.ImageService;

import java.io.IOException;

@RestController
@AllArgsConstructor
@RequestMapping("/image-upload")
public class ImageUploaderController {


    private final ImageService imageService;

    @PostMapping("/")
    public ResponseEntity<String> upload(@RequestBody MultipartFile file) throws IOException {
        return ResponseEntity.status(HttpStatus.OK).body(imageService.upload(file));
    }

}
