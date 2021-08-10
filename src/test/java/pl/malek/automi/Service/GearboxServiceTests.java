package pl.malek.automi.Service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.malek.automi.Mappers.GearboxMapper;
import pl.malek.automi.Repository.GearboxRepository;
import pl.malek.automi.Service.Impl.GearboxServiceImpl;

@ExtendWith(MockitoExtension.class)
public class GearboxServiceTests {

    @Mock
    private GearboxRepository gearboxRepository;

    @Mock
    private GearboxMapper gearboxMapper;

    @InjectMocks
    private GearboxServiceImpl gearboxService;

    @Test
    void shouldReturnAllGearbox() {

    }
}
