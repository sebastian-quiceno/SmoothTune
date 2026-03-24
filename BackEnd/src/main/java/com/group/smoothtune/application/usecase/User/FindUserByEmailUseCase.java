package com.group.smoothtune.application.usecase.User;

import com.group.smoothtune.domain.model.User;
import com.group.smoothtune.domain.port.UserRepository;

import java.util.Optional;

public class FindUserByEmailUseCase {

    private final UserRepository userRepository;

    public FindUserByEmailUseCase(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Optional<User> execute(String email) {
        return userRepository.findByEmail(email);
    }
}