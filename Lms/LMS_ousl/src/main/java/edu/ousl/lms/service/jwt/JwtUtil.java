package edu.ousl.lms.service.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtil {
    private String secret = "mySecretKey"; // Secret key for signing the JWT

    // Generate JWT Token
    public String generateToken(String email,long userId,String user) {
        return Jwts.builder()
                .setSubject(email)
                .claim("userID", userId)// Adding userID to the token payload
                .claim("userEmail", email)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 )) // 10 hours
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }

    // Validate JWT Token
    public boolean validateToken(String token, String username) {
        final String extractedUsername = extractUsername(token);
        return (extractedUsername.equals(username) && !isTokenExpired(token));
    }

    // Extract Username
    public String extractUsername(String token) {
        return extractClaims(token).getSubject();
    }

    // Extract Claims
    private Claims extractClaims(String token) {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
    }

    // Check if token is expired
    private boolean isTokenExpired(String token) {
        return extractClaims(token).getExpiration().before(new Date());
    }
}
