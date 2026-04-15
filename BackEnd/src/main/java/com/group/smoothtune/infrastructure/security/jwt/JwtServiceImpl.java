package com.group.smoothtune.infrastructure.security.jwt;

import com.group.smoothtune.application.dtos.AuthRequestDTO;
import com.group.smoothtune.domain.model.AuthRequest;
import com.group.smoothtune.domain.model.AuthResult;
import com.group.smoothtune.domain.port.TokenPort;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;


@Service
public class JwtServiceImpl implements TokenPort {

    private static final String SECRET_KEY = "UD32VQK58D7ogms7JZQAC2dUNIV3Zt8Jg3FHeO4+EZE="; // IMPORTANTE
    private final SecretKey key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
    private static final int expirationTime = 1000 * 60 * 60 * 24; // 24 horas

//    @Override
//    public String generateToken(AuthRequest authRequest) {
//
//        Map<String, Object> claims = new HashMap<>();
//        claims.put("userId", authResult.getUserId());
//        claims.put("email", authResult.getEmail());
//
//        return Jwts.builder()
//                .setClaims(claims)
//                .setSubject(authResult.getEmail())
//                .setIssuedAt(new Date(System.currentTimeMillis()))
//                .setExpiration(new Date(System.currentTimeMillis() + expirationTime))
//                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
//                .compact();
//    }

    @Override
    public String generateToken(String email){
        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis()+expirationTime))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    public String extractUsername(String token){
        return extractClaims(token).getSubject();
    }

//    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
//        return claimsResolver.apply(extractAllClaims(token));
//    }

    public Claims extractClaims(String token){
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();

    }

    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return username.equals(userDetails.getUsername()) && !isTokenExpired(token);
    }

    public boolean validateToken(String username, UserDetails userDetails, String token){
        return username.equals(userDetails.getUsername()) && !isTokenExpired(token);
    }

    public boolean isTokenExpired(String token){
        return extractClaims(token).getExpiration().before(new Date());
    }

}

