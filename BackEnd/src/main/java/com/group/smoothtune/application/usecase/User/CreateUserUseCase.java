package com.group.smoothtune.application.usecase.User;


import com.group.smoothtune.domain.model.User;
import com.group.smoothtune.domain.port.UserRepository;

public class CreateUserUseCase {

    private final UserRepository userRepository;

    public CreateUserUseCase(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User execute(String email, String encodedPassword, String username) {

        User user = new User(email, encodedPassword, username);

        return userRepository.save(user);
    }
}
