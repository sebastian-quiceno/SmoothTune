package com.group.smoothtune.application.usecase.User;

import com.group.smoothtune.domain.exception.UserNotFoundException;
import com.group.smoothtune.domain.exception.UsernameAlreadyExistException;
import com.group.smoothtune.domain.model.User;
import com.group.smoothtune.domain.port.UserRepository;

public class UpdateUserUseCase {
    private final UserRepository userRepository;

    public UpdateUserUseCase(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    //Para cambiar username
    public User actualizar(Long userId, String newUsername) {

        User oldUser = userRepository.findById(userId).orElseThrow(()-> new UserNotFoundException("No se encontro el usuario con el ID: "+userId));


        if(userRepository.findAll().stream().anyMatch(user -> user.getUsername().equals(newUsername))){
            throw new UsernameAlreadyExistException("El nombre de usuario "+newUsername+" ya existe");
        }

        oldUser.setUsername(newUsername);

        return userRepository.save(oldUser);
    }
}
