package com.group.smoothtune.infrastructure.security.jwt;

import com.group.smoothtune.infrastructure.security.auth.CustomUserDetailsService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtServiceImpl jwtServiceImpl;
    private final CustomUserDetailsService userDetailsService;

    public JwtAuthenticationFilter(JwtServiceImpl jwtServiceImpl, CustomUserDetailsService userDetailsService) {
        this.jwtServiceImpl = jwtServiceImpl;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String path = request.getServletPath();

        // Ignora login y register
        if (path.startsWith("/api/auth") || path.startsWith("/file") || path.startsWith("/h2-console")) {
            filterChain.doFilter(request, response);
            return;
        }

        //Extrae el tocken del header, en la parte llamada "Authorization"
        String authHeader = request.getHeader("Authorization");
        String token = "";
        String username = "";

        System.out.println("Authentificando");

        //Si no hay token sale
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        System.out.println("Sacando token y usuario...");

        token = authHeader.substring(7);
        username = jwtServiceImpl.extractUsername(token);

        System.out.println("El usuario es: "+username);

        if(username != null && SecurityContextHolder.getContext().getAuthentication() == null){
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);

            if(jwtServiceImpl.validateToken(username, userDetails, token)){
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }
        filterChain.doFilter(request, response);

    }

}
