package com.group.smoothtune.aplication.auth;

import com.group.smoothtune.dtos.AuthResponseDTO;
import com.group.smoothtune.dtos.SignInRequestDTO;
import com.group.smoothtune.infrastructure.security.JwtService;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class SignInService {

    private final AuthenticationManager authManager;
    private final JwtService jwtService;

    public AuthResponseDTO execute(SignInRequestDTO dto) {

        Authentication authentication =
                authManager.authenticate(
                        new UsernamePasswordAuthenticationToken(
                                dto.email(),
                                dto.password()
                        )
                );

        String token = jwtService.generateToken(
                (UserDetails) authentication.getPrincipal()
        );

        return new AuthResponseDTO(token);
    }
}
