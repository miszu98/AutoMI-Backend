package pl.malek.automi.Service.Impl;

import com.cloudinary.Cloudinary;
import com.cloudinary.EagerTransformation;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import pl.malek.automi.Service.ImageService;
import java.io.IOException;
import java.util.Arrays;


@Service
public class ImageServiceImpl implements ImageService {

    private Cloudinary cloudinary;

    public ImageServiceImpl(@Value("${CLOUDINARY_URL}") String config) {
        cloudinary = new Cloudinary(config);
    }

    @Override
    public String upload(MultipartFile file) throws IOException {
        var map = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.asMap(
                "eager", Arrays.asList(
                        new EagerTransformation().width(400).height(300).crop("pad"),
                        new EagerTransformation().width(260).height(200).crop("crop").gravity("north")
                )));
        return (String) map.get("url");
    }
}
