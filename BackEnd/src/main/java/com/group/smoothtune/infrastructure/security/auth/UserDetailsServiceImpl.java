package com.group.smoothtune.infrastructure.security.auth;

import com.group.smoothtune.infrastructure.persistence.entity.UserEntity;
import com.group.smoothtune.infrastructure.persistence.repository.UserJpaRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl {
//    private final UserJpaRepository userJpaRepository;
//
//    public UserDetailsServiceImpl(UserJpaRepository userJpaRepository) {
//        this.userJpaRepository = userJpaRepository;
//    }
//
//    @Override
//    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
//
//        UserEntity user = userJpaRepository.findByEmail(email)
//                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));
//
//        return User.builder()
//                .username(user.getEmail())
//                .password(user.getPassword())
//                .roles("USER")
//                .build();
//    }
}
