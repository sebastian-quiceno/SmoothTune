package com.group.smoothtune.infrastructure.security.jwt;

import io.jsonwebtoken.Claims;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class JwtServiceImplTest {

    private JwtServiceImpl jwtService;

    @BeforeEach
    void setUp() {
        jwtService = new JwtServiceImpl();
        // Usamos una clave secreta larga de al menos 256 bits para cumplir con HS256
        ReflectionTestUtils.setField(jwtService, "SECRET_KEY", "mysecretkeywhichisverylongandsecureenoughforhs256");
        ReflectionTestUtils.setField(jwtService, "expirationTime", 3600000L); // 1 hora
        jwtService.init();
    }

    @Test
    void shouldGenerateTokenAndExtractUsername() {
        String email = "test@example.com";
        String token = jwtService.generateToken(email);

        assertNotNull(token);
        String extractedUsername = jwtService.extractUsername(token);
        assertEquals(email, extractedUsername);
    }

    @Test
    void shouldExtractClaims() {
        String email = "test@example.com";
        String token = jwtService.generateToken(email);

        Claims claims = jwtService.extractClaims(token);

        assertNotNull(claims);
        assertEquals(email, claims.getSubject());
        assertNotNull(claims.getIssuedAt());
        assertNotNull(claims.getExpiration());
    }

    @Test
    void shouldValidateTokenSuccessfully() {
        String email = "test@example.com";
        String token = jwtService.generateToken(email);
        UserDetails userDetails = new User(email, "password", new ArrayList<>());

        boolean isValid = jwtService.isTokenValid(token, userDetails);
        assertTrue(isValid);

        boolean isAlsoValid = jwtService.validateToken(email, userDetails, token);
        assertTrue(isAlsoValid);
    }

    @Test
    void shouldDetectTokenAsNotExpired() {
        String email = "test@example.com";
        String token = jwtService.generateToken(email);

        boolean isExpired = jwtService.isTokenExpired(token);
        assertFalse(isExpired);
    }
}
