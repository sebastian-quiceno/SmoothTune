package com.group.smoothtune.infrastructure.security.auth;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

public class CustomUserDetails{
//    private final Long userId;
//    private final String email;
//    private final String password;
//    private final boolean enabled;
//
//    // Constructor con los atributos de tu dominio
//    public CustomUserDetails(Long userId, String email, String password, boolean enabled) {
//        this.userId = userId;
//        this.email = email;
//        this.password = password;
//        this.enabled = enabled;
//    }
//
//    // Métodos requeridos por UserDetails
//
//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        // Aquí puedes mapear roles o permisos
//        // Ejemplo básico: "ROLE_USER" o "ROLE_ADMIN"
//        return Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"));
//    }
//
//    @Override
//    public String getPassword() {
//        return password;  // Devuelves la contraseña encriptada
//    }
//
//    @Override
//    public String getUsername() {
//        return email;  // Devuelves el email (o username)
//    }
//
//    @Override
//    public boolean isAccountNonExpired() {
//        return true;  // Puedes agregar lógica si tu cuenta puede expirar
//    }
//
//    @Override
//    public boolean isAccountNonLocked() {
//        return true;  // Puedes agregar lógica si tu cuenta está bloqueada
//    }
//
//    @Override
//    public boolean isCredentialsNonExpired() {
//        return true;  // Puedes agregar lógica si las credenciales del usuario pueden expirar
//    }
//
//    @Override
//    public boolean isEnabled() {
//        return enabled;  // Devuelves si el usuario está habilitado
//    }
//
//    public Long getUserId() {
//        return userId;
//    }
}
