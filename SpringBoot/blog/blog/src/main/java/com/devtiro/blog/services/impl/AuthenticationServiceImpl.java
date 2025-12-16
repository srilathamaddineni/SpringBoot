package com.devtiro.blog.services.impl;

import com.devtiro.blog.services.AuthenticationService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthenticationServiceImpl implements AuthenticationService {
    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;

    @Value("${jwt.secret}")
    private String secretKey;

    private final Long jwtExpiryMs=86400000L;
    @Override
    public UserDetails authenticate(String email, String password) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(email,password)
        );
        return userDetailsService.loadUserByUsername(email);
    }

    @Override
    public String generateToken(UserDetails userDetails) {
        Map<String,Object> claims=new HashMap<>();
           return Jwts.builder()
                   .setClaims(claims)
                   .setSubject(userDetails.getUsername())
                   .setIssuedAt(new Date(System.currentTimeMillis()))
                   .setExpiration(new Date(System.currentTimeMillis() + jwtExpiryMs))
                   .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                   .compact();


    }

    @Override
    public UserDetails validateToken(String token) {
        try {
            // This will throw exceptions if token is invalid or expired
            Jwts.parserBuilder()
                    .setSigningKey(getSigningKey())
                    .build()
                    .parseClaimsJws(token);

            // Only if no exception, extract username and load user
            String userName = extractUserName(token);
            return userDetailsService.loadUserByUsername(userName);

        } catch (Exception e) {
            // Log or handle the exception, token invalid or expired
            log.warn("JWT token invalid or expired: {}", e.getMessage());
            return null;  // indicate invalid token
        }
    }

    private String extractUserName(String token)
    {
        Claims claims=Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
        return claims.getSubject();

    }

    private Key getSigningKey()
    {
        byte[] keyBytes=secretKey.getBytes();
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
