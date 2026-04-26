package com.group.smoothtune.infrastructure.security.jwt;

import com.group.smoothtune.infrastructure.security.auth.CustomUserDetailsService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class JwtAuthenticationFilterTest {

    @Mock
    private JwtServiceImpl jwtServiceImpl;

    @Mock
    private CustomUserDetailsService userDetailsService;

    @Mock
    private FilterChain filterChain;

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @InjectMocks
    private JwtAuthenticationFilter filter;

    @BeforeEach
    void setUp() {
        SecurityContextHolder.clearContext();
    }

    @AfterEach
    void tearDown() {
        SecurityContextHolder.clearContext();
    }

    @Test
    void shouldIgnoreAuthPaths() throws ServletException, IOException {
        when(request.getServletPath()).thenReturn("/api/auth/login");

        filter.doFilterInternal(request, response, filterChain);

        verify(filterChain).doFilter(request, response);
        verifyNoInteractions(jwtServiceImpl, userDetailsService);
    }

    @Test
    void shouldIgnoreMissingAuthHeader() throws ServletException, IOException {
        when(request.getServletPath()).thenReturn("/api/users");
        when(request.getHeader("Authorization")).thenReturn(null);

        filter.doFilterInternal(request, response, filterChain);

        verify(filterChain).doFilter(request, response);
        verifyNoInteractions(jwtServiceImpl, userDetailsService);
    }

    @Test
    void shouldIgnoreInvalidAuthHeader() throws ServletException, IOException {
        when(request.getServletPath()).thenReturn("/api/users");
        when(request.getHeader("Authorization")).thenReturn("InvalidHeader ");

        filter.doFilterInternal(request, response, filterChain);

        verify(filterChain).doFilter(request, response);
        verifyNoInteractions(jwtServiceImpl, userDetailsService);
    }

    @Test
    void shouldAuthenticateValidToken() throws ServletException, IOException {
        String token = "valid-token";
        String username = "test@example.com";
        when(request.getServletPath()).thenReturn("/api/users");
        when(request.getHeader("Authorization")).thenReturn("Bearer " + token);

        UserDetails userDetails = new User(username, "password", new ArrayList<>());

        when(jwtServiceImpl.extractUsername(token)).thenReturn(username);
        when(userDetailsService.loadUserByUsername(username)).thenReturn(userDetails);
        when(jwtServiceImpl.validateToken(username, userDetails, token)).thenReturn(true);

        when(request.getRemoteAddr()).thenReturn("127.0.0.1");
        when(request.getSession(false)).thenReturn(null);

        filter.doFilterInternal(request, response, filterChain);

        verify(jwtServiceImpl).extractUsername(token);
        verify(userDetailsService).loadUserByUsername(username);
        verify(jwtServiceImpl).validateToken(username, userDetails, token);
        verify(filterChain).doFilter(request, response);

        assertNotNull(SecurityContextHolder.getContext().getAuthentication());
        assertEquals(username, SecurityContextHolder.getContext().getAuthentication().getName());
    }
}
