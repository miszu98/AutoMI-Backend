package pl.malek.automi.service.Impl;

import com.cloudinary.Cloudinary;
import com.cloudinary.EagerTransformation;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import pl.malek.automi.entity.ImageEntity;
import pl.malek.automi.exception.CarOfferNotFoundException;
import pl.malek.automi.repository.CarOfferRepository;
import pl.malek.automi.repository.ImageRepository;
import pl.malek.automi.service.ImageService;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@Service
public class ImageServiceImpl implements ImageService {

    private Cloudinary cloudinary;
    private ImageRepository imageRepository;
    private CarOfferRepository carOfferRepository;

    public ImageServiceImpl(@Value("${CLOUDINARY_URL}") String config, ImageRepository imageRepository, CarOfferRepository carOfferRepository) {
        cloudinary = new Cloudinary(config);
        this.imageRepository = imageRepository;
        this.carOfferRepository = carOfferRepository;
    }

    @Override
    public List<String> upload(MultipartFile[] files, Long offerId) throws IOException, CarOfferNotFoundException {
        var links = new ArrayList<String>();

        var foundedOffer = carOfferRepository.findById(offerId).orElseThrow(
                () -> new CarOfferNotFoundException(String.format("Not found Car offer with id: %d", offerId))
        );

        for (MultipartFile image : files) {
            var map = cloudinary.uploader().upload(image.getBytes(), ObjectUtils.asMap(
                    "eager", Arrays.asList(
                            new EagerTransformation().width(400).height(300).crop("pad"),
                            new EagerTransformation().width(260).height(200).crop("crop").gravity("north")
                    )));

            var link = (String) map.get("url");
            links.add(link);

            var imgEntity = ImageEntity.builder().url(link).carOfferEntity(foundedOffer).build();

            imageRepository.save(imgEntity);
        }

        return links;
    }

    @Override
    public List<String> upload(List<String> files, Long offerId) throws CarOfferNotFoundException {
        var foundedOffer = carOfferRepository.findById(offerId).orElseThrow(
                () -> new CarOfferNotFoundException(String.format("Not found Car offer with id: %d", offerId))
        );

        for (String link : files) {
            var imgEntity = ImageEntity.builder().url(link).carOfferEntity(foundedOffer).build();
            imageRepository.save(imgEntity);
        }

        return files;
    }

    @Override
    @Transactional
    public void deleteImagesByOfferId(Long id) {
        imageRepository.deleteImagesByOfferId(id);
    }
}
