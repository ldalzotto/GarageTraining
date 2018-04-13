package com.ldz.user;

import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Integer getCarNumberFromUser(Long userId) {
        return this.userRepository.findById(userId).get().getGarages().size();
    }
}
