package pl.malek.automi.Config;

import org.springframework.context.annotation.Configuration;
import pl.malek.automi.DTO.Mark;
import pl.malek.automi.DTO.Model;
import pl.malek.automi.Entities.MarkEntity;
import pl.malek.automi.Entities.ModelEntity;
import pl.malek.automi.Exceptions.MarkNotFoundException;
import pl.malek.automi.Mappers.MarkMapper;
import pl.malek.automi.Mappers.ModelMapper;
import pl.malek.automi.Repository.*;
import pl.malek.automi.Service.MarkService;
import pl.malek.automi.Service.ModelService;


@Configuration
public class DataConfig {

    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private CarRepository carRepository;
    private ModelRepository modelRepository;
    private MarkRepository markRepository;
    private ColorRepository colorRepository;
    private FuelTypeRepository fuelTypeRepository;
    private DrivingGearRepository drivingGearRepository;
    private CarOfferRepository carOfferRepository;

    private MarkService markService;
    private ModelService modelService;
    private ModelMapper modelMapper;


    public DataConfig(ModelMapper modelMapper, UserRepository userRepository, RoleRepository roleRepository, CarRepository carRepository, ModelRepository modelRepository, MarkRepository markRepository, ColorRepository colorRepository, FuelTypeRepository fuelTypeRepository, DrivingGearRepository drivingGearRepository, CarOfferRepository carOfferRepository, MarkService markService, ModelService modelService) throws MarkNotFoundException {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.carRepository = carRepository;
        this.modelRepository = modelRepository;
        this.markRepository = markRepository;
        this.colorRepository = colorRepository;
        this.fuelTypeRepository = fuelTypeRepository;
        this.drivingGearRepository = drivingGearRepository;
        this.carOfferRepository = carOfferRepository;
        this.markService = markService;
        this.modelService = modelService;
        this.modelMapper = modelMapper;


        Mark bmw = Mark.builder().mark("BMW").build();
        Mark mercedes = Mark.builder().mark("Mercedes-Benz").build();

        Model m5 = Model.builder().model("M5").markId(1L).build();
        Model c63s = Model.builder().model("C63s").markId(2L).build();

        markRepository.save(MarkMapper.dtoToEntity(bmw));
        markRepository.save(MarkMapper.dtoToEntity(mercedes));

        modelRepository.save(modelMapper.dtoToEntity(m5));
        modelRepository.save(modelMapper.dtoToEntity(c63s));

//
        MarkEntity markEntity = markService.getById(1L);
        System.out.println(markService.getByMark("BMW"));
//
//        System.out.println(ModelMapper.entitiesToDto(markEntity.getModels()));
//        System.out.println(modelRepository.findAll());





    }
}
