package com.group.smoothtune.infrastructure.security.auth;

import com.group.smoothtune.domain.model.AuthResult;
import com.group.smoothtune.domain.port.AuthenticatePort;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
public class SpringSecurityAuthAdapter implements AuthenticatePort {

    private final AuthenticationManager authenticationManager;

    // Constructor: Inyectas el AuthenticationManager de Spring Security
    public SpringSecurityAuthAdapter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    public AuthResult authenticate(String email, String password) {

        // Crear el objeto de autenticación con las credenciales proporcionadas
        Authentication authentication = new UsernamePasswordAuthenticationToken(email, password);

        // El AuthenticationManager de Spring Security hace el trabajo de validación de la contraseña
        Authentication auth = authenticationManager.authenticate(authentication);

        // Obtienes el UserDetails del Authentication
        UserDetails user = (UserDetails) auth.getPrincipal();

        // Devuelves un AuthResult con los datos que necesitas
        return new AuthResult(
                user.getUsername(),  // Aquí va el email
                user.getPassword()   // Aquí puede ir otro dato necesario como el userId si lo tienes en UserDetails
        );
    }
}