package com.ldz.login;

import com.ldz.user.User;
import com.ldz.user.UserConstants;
import com.ldz.user.UserRepository;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.data.rest.webmvc.support.RepositoryEntityLinks;
import org.springframework.hateoas.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@RepositoryRestController
public class LoginController {

    private final LoginRepository loginRepository;
    private final UserRepository userRepository;
    private final RepositoryEntityLinks repositoryEntityLinks;

    public LoginController(LoginRepository loginRepository, UserRepository userRepository, RepositoryEntityLinks repositoryEntityLinks) {
        this.loginRepository = loginRepository;
        this.userRepository = userRepository;
        this.repositoryEntityLinks = repositoryEntityLinks;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/" + LoginConstants.LOGIN_RESSOURCES)
    @ResponseBody
    public Resource<LoginEvent> createLoginEvent(@RequestBody LoginEvent loginEvent) {

        String username = loginEvent.getUsername();
        User foundedUser = this.userRepository.findUserByUsername(username);
        if (foundedUser != null) {
            loginEvent.setUser(foundedUser);
            LoginEvent savedLogin = this.loginRepository.save(loginEvent);

            Resource<LoginEvent> garageResource = new Resource<>(savedLogin);
            garageResource.add(repositoryEntityLinks.linkToSingleResource(LoginRepository.class, savedLogin.getId()).withSelfRel());
            garageResource.add(repositoryEntityLinks.linkToSingleResource(UserRepository.class, foundedUser.getId()).withRel(UserConstants.USER_RESSOURCE));
            return garageResource;
        } else {
            throw new RuntimeException("User doesn't exist.");
        }
    }
}
