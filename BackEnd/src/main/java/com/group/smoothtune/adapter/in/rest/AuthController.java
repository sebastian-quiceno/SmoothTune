package com.group.smoothtune.adapter.in.rest;

import com.group.smoothtune.adapter.in.rest.dtos.AuthResponseDTO;
import com.group.smoothtune.adapter.in.rest.dtos.SignInRequestDTO;
import com.group.smoothtune.adapter.in.rest.dtos.SignUpRequestDTO;
import com.group.smoothtune.application.usecase.auth.SignInUseCase;
import com.group.smoothtune.application.usecase.auth.SignUpUseCase;
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

    @PostMapping({"/signin", "/signIn"})
    public ResponseEntity<AuthResponseDTO> signIn(@RequestBody SignInRequestDTO request) {
        AuthResponseDTO response = signInUseCase.execute(request);
        return ResponseEntity.ok(response);
    }

    @PostMapping({"/signup", "/signUp"})
    public ResponseEntity<AuthResponseDTO> signUp(@RequestBody SignUpRequestDTO request) {
        AuthResponseDTO response = signUpUseCase.execute(request);
        return ResponseEntity.ok(response);
    }
}
