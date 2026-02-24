package com.group.smoothtune.aplication.auth;

import com.group.smoothtune.dtos.AuthResponseDTO;
import com.group.smoothtune.dtos.SignUpRequestDTO;
import com.group.smoothtune.domain.interfaces.UserRepository;
import com.group.smoothtune.domain.model.User;
import com.group.smoothtune.infrastructure.security.JwtService;
import com.group.smoothtune.infrastructure.security.UserDetailsServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class SignUpService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final UserDetailsServiceImpl userDetailsService;

    public AuthResponseDTO execute(SignUpRequestDTO dto) {

        // Email único
        userRepository.findByEmail(dto.email())
                .ifPresent(user -> {
                    throw new IllegalArgumentException("El email ya está registrado");
                });

        // Username único
        userRepository.findByUsername(dto.username())
                .ifPresent(user -> {
                    throw new IllegalArgumentException("El nombre de usuario ya está en uso");
                });

        // Hash password
        String encodedPassword = passwordEncoder.encode(dto.password());

        // Crear usuario de dominio
        User user = new User(
                null,
                dto.email(),
                dto.username()
        );

        // Persistir
        userRepository.save(user, encodedPassword);

        // Autenticar automáticamente
        UserDetails userDetails =
                userDetailsService.loadUserByUsername(dto.email());

        // JWT
        String token = jwtService.generateToken(userDetails);

        return new AuthResponseDTO(token);
    }
}
