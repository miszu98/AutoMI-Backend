package pl.malek.automi.service;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.malek.automi.mapper.CarOfferMapper;
import pl.malek.automi.repository.CarOfferRepository;
import pl.malek.automi.service.Impl.CarOfferServiceImpl;

@ExtendWith(MockitoExtension.class)
public class CarOfferServiceTests {

    @Mock
    private CarOfferRepository carOfferRepository;

    @Mock
    private CarOfferMapper carOfferMapper;

    @Mock
    private CarOfferServiceImpl carOfferService;





}
