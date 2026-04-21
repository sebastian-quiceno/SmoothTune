package com.group.smoothtune.infrastructure.security.auth;

import com.group.smoothtune.domain.model.User;
import com.group.smoothtune.domain.port.UserRepository;
import com.group.smoothtune.infrastructure.persistence.entity.UserEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        // Aquí consultas tu base de datos usando el repositorio de usuarios
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found by email"));

        // Aquí se mapea el User a CustomUserDetails
        return new CustomUserDetails(user.getId(), user.getEmail(), user.getPassword());
    }
}
