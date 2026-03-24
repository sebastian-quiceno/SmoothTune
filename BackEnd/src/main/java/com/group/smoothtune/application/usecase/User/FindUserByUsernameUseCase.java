package com.group.smoothtune.application.usecase.User;

import com.group.smoothtune.domain.model.User;
import com.group.smoothtune.domain.port.UserRepository;

import java.util.Optional;

public class FindUserByUsernameUseCase {

    private final UserRepository userRepository;

    public FindUserByUsernameUseCase(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Optional<User> execute(String username) {
        return userRepository.findByUsername(username);
    }
}
