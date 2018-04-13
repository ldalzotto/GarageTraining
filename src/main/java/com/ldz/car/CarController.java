package com.ldz.car;

import com.ldz.garage.Garage;
import com.ldz.garage.GarageConstants;
import com.ldz.garage.GarageRepository;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.data.rest.webmvc.support.RepositoryEntityLinks;
import org.springframework.hateoas.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RepositoryRestController
public class CarController {


    private final CarRepository carRepository;
    private final GarageRepository garageRepository;
    private final RepositoryEntityLinks repositoryEntityLinks;

    public CarController(CarRepository carRepository, GarageRepository garageRepository, RepositoryEntityLinks repositoryEntityLinks) {
        this.carRepository = carRepository;
        this.garageRepository = garageRepository;
        this.repositoryEntityLinks = repositoryEntityLinks;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/" + GarageConstants.GARAGE_RESSOURCE + "/{garage-id}/" + CarContants.CAR_RESSOURCE)
    @ResponseBody
    public Resource<Car> createCar(@PathVariable("garage-id") Long garageId, @RequestBody Car car) {
        Garage garage = this.garageRepository.findById(garageId).get();
        car.setGarage(garage);
        garage.getCars().add(car);

        Car savedCar = this.carRepository.save(car);
        this.garageRepository.save(garage);

        Resource<Car> carResource = new Resource<>(savedCar);
        carResource.add(repositoryEntityLinks.linkToSingleResource(CarRepository.class, savedCar.getId()).withSelfRel());
        carResource.add(repositoryEntityLinks.linkToSingleResource(GarageRepository.class, garageId).withRel(GarageConstants.GARAGE_RESSOURCE));
        return carResource;
    }

}
