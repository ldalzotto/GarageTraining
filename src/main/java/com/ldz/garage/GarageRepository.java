package com.ldz.garage;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = GarageConstants.GARAGE_RESSOURCE)
public interface GarageRepository extends PagingAndSortingRepository<Garage, Long> {
}
