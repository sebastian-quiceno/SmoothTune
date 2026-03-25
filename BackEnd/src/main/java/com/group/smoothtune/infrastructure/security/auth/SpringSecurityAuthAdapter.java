package com.group.smoothtune.infrastructure.security.auth;

import com.group.smoothtune.domain.model.AuthResult;
import com.group.smoothtune.domain.port.AuthenticatePort;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Component
public class SpringSecurityAuthAdapter implements AuthenticatePort {

    private final AuthenticationManager authenticationManager;

    public SpringSecurityAuthAdapter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    public AuthResult authenticate(String email, String password) {

        Authentication authentication =
                new UsernamePasswordAuthenticationToken(email, password);

        Authentication auth = authenticationManager.authenticate(authentication);

        CustomUserDetails user = (CustomUserDetails) auth.getPrincipal();

        return new AuthResult(
                user.getUserId(),
                user.getUsername()
        );
    }
}