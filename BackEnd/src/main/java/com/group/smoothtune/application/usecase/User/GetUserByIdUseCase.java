package com.group.smoothtune.application.usecase.User;


import com.group.smoothtune.domain.exception.UserNotFoundException;
import com.group.smoothtune.domain.model.User;
import com.group.smoothtune.domain.port.UserRepository;

public class GetUserByIdUseCase {

    private final UserRepository userRepository;

    public GetUserByIdUseCase(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User execute(Long id){
        return userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("No se encontro el usuario con el ID: "+id));
    }
}
