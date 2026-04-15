package com.group.smoothtune.prueba;

import com.group.smoothtune.infrastructure.persistence.entity.UserEntity;
import com.group.smoothtune.infrastructure.persistence.repository.UserJpaRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class DataInitializer {
    @Bean
    CommandLineRunner initUsers(UserJpaRepository userRepo) {
        return args -> {
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

            if (userRepo.count() == 0) { // solo si no hay usuarios
                UserEntity user = UserEntity.builder()
                        .email("prueba@test.com")
                        .username("Prueba")
                        .password(encoder.encode("prueba123")) // contraseña clara -> hash
                        .build();

                userRepo.save(user);
                System.out.println("Usuario inicial creado");
            }
        };
    }
}
