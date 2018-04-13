package com.ldz.car;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = CarContants.CAR_RESSOURCE)
public interface CarRepository extends PagingAndSortingRepository<Car, Long> {
}
