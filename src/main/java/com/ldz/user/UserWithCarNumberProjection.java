package com.ldz.user;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.rest.core.config.Projection;

@Projection(types = User.class, name = "userWithCarNumber")
public interface UserWithCarNumberProjection {

    String getUsername();

    String getFirstname();

    String getLastname();

    String getCity();

    @Value("#{@userService.getCarNumberFromUser(target.id)}")
    Long getCarnumber();
}
