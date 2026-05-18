package com.group.smoothtune.adapter.in.rest;

import com.group.smoothtune.adapter.in.rest.dtos.response.UserResponseDTO;
import com.group.smoothtune.adapter.in.rest.mapper.UserMapper;
import com.group.smoothtune.application.usecase.User.GetUserByIdUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final GetUserByIdUseCase getUserByIdUseCase;

    public UserController(GetUserByIdUseCase getUserByIdUseCase) {
        this.getUserByIdUseCase = getUserByIdUseCase;
    }

    @GetMapping("/getUser/{id}")
    public ResponseEntity<UserResponseDTO> getUserById(@PathVariable Long id){
        return ResponseEntity.ok().body(UserMapper.toResponse(getUserByIdUseCase.execute(id)));
    }

}
