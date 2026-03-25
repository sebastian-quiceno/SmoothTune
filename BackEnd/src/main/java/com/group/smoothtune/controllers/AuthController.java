package com.group.smoothtune.controllers;

import com.group.smoothtune.application.dtos.AuthResponseDTO;
import com.group.smoothtune.application.dtos.SignInRequestDTO;
import com.group.smoothtune.application.dtos.SignUpRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
//    Se desabilito mientras se corrige

//    private final SignUpService signUpService;
//    private final SignInService signInService;
//
//    // SIGN UP
//    @PostMapping("/signup")
//    @ResponseStatus(HttpStatus.CREATED)
//    public AuthResponseDTO signUp(@RequestBody SignUpRequestDTO request){
//        return signUpService.execute(request);
//    }
//
//    // SIGN IN
//    @PostMapping("/signin")
//    public AuthResponseDTO signIn(@RequestBody SignInRequestDTO request){
//        return signInService.execute(request);
//    }
}
