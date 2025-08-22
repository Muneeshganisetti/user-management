package com.muneesh.config;

import com.muneesh.Entity.User;
import com.muneesh.enu.Role;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.Jwts;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class Jwt {

    private final SecretKey key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    private final long expiry = 3600L; // in seconds


    public String generateToken(User user) {
        Map<String, Object> claims = new HashMap<>();
        // Store roles in the correct Spring Security format
        claims.put("roles", List.of("ROLE_" + user.getRole().name().toUpperCase()));
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(user.getId().toString()) // user ID as subject
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expiry * 1000))
                .signWith(key)
                .compact();
    }

    public long getExpiry() {
        return expiry;
    }

    public String extractUsername(String token) {
        return extractAllClaims(token).getSubject();
    }

    public Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername())) && !isTokenExpired(token);
    }

    public boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    public Date extractExpiration(String token) {
        return extractAllClaims(token).getExpiration();
    }


    }

