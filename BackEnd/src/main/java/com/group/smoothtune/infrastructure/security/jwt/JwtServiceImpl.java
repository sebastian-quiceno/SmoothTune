package com.group.smoothtune.infrastructure.security.jwt;

import com.group.smoothtune.domain.model.AuthResult;
import com.group.smoothtune.domain.port.TokenPort;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;


@Service
public class JwtServiceImpl implements TokenPort {

    private static final String SECRET_KEY = "0a19a1e7b6b939001375e8f07c0850fa0c2c70b258408357f4b21fe77d43bda2";
    private static final int expirationTime = 1000 * 60 * 24; // 24 minutos, se puede extender a 1 día

    @Override
    public String generateToken(AuthResult authResult) {
        // Crear un mapa de claims que contiene la información que quieres incluir en el token
        Map<String, Object> claims = new HashMap<>();
        claims.put("userId", authResult.getUserId()); // Aquí usamos el ID del usuario
        claims.put("email", authResult.getEmail());   // Email del usuario

        return Jwts.builder()
                .setClaims(claims) // Claims que agregamos
                .setSubject(authResult.getEmail()) // El "subject" es típicamente el email
                .setIssuedAt(new Date(System.currentTimeMillis())) // Fecha de emisión
                .setExpiration(new Date(System.currentTimeMillis() + expirationTime)) // Fecha de expiración
                .signWith(getSigningKey(), SignatureAlgorithm.HS256) // Firma con la clave secreta
                .compact(); // Compilamos el token
    }

    // Método para extraer un claim del token (como "email", "userId", etc.)
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    // Método para extraer todos los claims del token
    public Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey()) // Usamos la clave secreta para la validación
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    // Validar si el token es válido (es decir, si no ha sido alterado)
    public boolean isTokenValid(String token) {
        try {
            extractAllClaims(token); // Si se puede extraer los claims, es válido
            return true;
        } catch (JwtException e) {
            return false; // Si hay un error al extraer los claims, el token es inválido
        }
    }

    // Verificar si el token ha expirado
    public boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date()); // Compara la fecha de expiración
    }

    // Extraer la fecha de expiración del token
    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    // Clave secreta para firmar el token (debe ser segura en producción)
    private Key getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY); // Decodificamos la clave secreta
        return Keys.hmacShaKeyFor(keyBytes); // Creamos la clave para firmar
    }
}

