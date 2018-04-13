package com.ldz.login;

import org.springframework.data.repository.CrudRepository;

public interface LoginRepository extends CrudRepository<LoginEvent, Long> {
}
