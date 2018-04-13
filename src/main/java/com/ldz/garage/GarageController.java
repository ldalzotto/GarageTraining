package com.ldz.garage;

import com.ldz.car.CarContants;
import com.ldz.user.User;
import com.ldz.user.UserConstants;
import com.ldz.user.UserRepository;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.data.rest.webmvc.support.RepositoryEntityLinks;
import org.springframework.hateoas.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RepositoryRestController
public class GarageController {

    private final GarageRepository garageRepository;
    private final UserRepository userRepository;
    private final RepositoryEntityLinks repositoryEntityLinks;

    public GarageController(GarageRepository garageRepository, UserRepository userRepository, RepositoryEntityLinks repositoryEntityLinks) {
        this.garageRepository = garageRepository;
        this.userRepository = userRepository;
        this.repositoryEntityLinks = repositoryEntityLinks;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(path = "/" + UserConstants.USER_RESSOURCES + "/{user-id}/" + GarageConstants.GARAGE_RESSOURCE + "")
    @ResponseBody
    public Resource<Garage> create(@PathVariable(name = "user-id") Long userId, @RequestBody Garage garage) {
        User foundedUser = this.userRepository.findById(userId).get();
        garage.setUser(foundedUser);
        Garage savedGarage = this.garageRepository.save(garage);
        foundedUser.getGarages().add(savedGarage);
        this.userRepository.save(foundedUser);

        Resource<Garage> garageResource = new Resource<>(savedGarage);
        garageResource.add(repositoryEntityLinks.linkToSingleResource(GarageRepository.class, savedGarage.getId()).withSelfRel());
        garageResource.add(repositoryEntityLinks.linkFor(GarageRepository.class).slash(savedGarage.getId()).slash(CarContants.CAR_RESSOURCE).withRel(CarContants.CAR_RESSOURCE));
        garageResource.add(repositoryEntityLinks.linkToSingleResource(UserRepository.class, userId).withRel(UserConstants.USER_RESSOURCES));
        return garageResource;
    }

}
