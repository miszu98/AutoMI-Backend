package pl.malek.automi.Service.Impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.malek.automi.Mappers.CarMapper;
import pl.malek.automi.Repository.CarRepository;

@Service
@AllArgsConstructor
public class CarServiceImpl {

    private final CarRepository carRepository;
    private final CarMapper carMapper;




}
