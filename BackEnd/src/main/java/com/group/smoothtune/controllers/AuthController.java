package com.group.smoothtune.controllers;

import com.group.smoothtune.application.dtos.AuthResponseDTO;
import com.group.smoothtune.application.dtos.SignInRequestDTO;
import com.group.smoothtune.application.dtos.SignUpRequestDTO;
import com.group.smoothtune.application.usecase.auth.SignInUseCase;
import com.group.smoothtune.application.usecase.auth.SignUpUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final SignInUseCase signInUseCase;
    private final SignUpUseCase signUpUseCase;

    public AuthController(SignInUseCase signInUseCase, SignUpUseCase signUpUseCase) {
        this.signInUseCase = signInUseCase;
        this.signUpUseCase = signUpUseCase;
    }

    @PostMapping("/signin")
    public ResponseEntity<AuthResponseDTO> signIn(@RequestBody SignInRequestDTO request) {

        System.out.println("ENTRÓ AL SIGNIN");
        AuthResponseDTO response = signInUseCase.execute(request);

        return ResponseEntity.ok(response);
    }

    @PostMapping("/signup")
    public ResponseEntity<AuthResponseDTO> signUp(@RequestBody SignUpRequestDTO request) {

        AuthResponseDTO response = signUpUseCase.execute(request);

        return ResponseEntity.ok(response);
    }
}
