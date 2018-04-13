package com.ldz.user;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

@RepositoryRestResource(path = UserConstants.USER_RESSOURCES)
public interface UserRepository extends PagingAndSortingRepository<User, Long> {

    @RestResource(path = "findByUsername", rel = "findByUsername")
    User findUserByUsername(@Param("username") String username);

}
