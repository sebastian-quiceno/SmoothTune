package com.group.smoothtune.controllers;

import com.group.smoothtune.dtos.AuthResponseDTO;
import com.group.smoothtune.dtos.SignInRequestDTO;
import com.group.smoothtune.aplication.auth.SignInService;
import com.group.smoothtune.aplication.auth.SignUpService;
import com.group.smoothtune.dtos.SignUpRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final SignUpService signUpService;
    private final SignInService signInService;

    // SIGN UP
    @PostMapping("/signup")
    @ResponseStatus(HttpStatus.CREATED)
    public AuthResponseDTO signUp(@RequestBody SignUpRequestDTO request){
        return signUpService.execute(request);
    }

    // SIGN IN
    @PostMapping("/signin")
    public AuthResponseDTO signIn(@RequestBody SignInRequestDTO request){
        return signInService.execute(request);
    }
}
