package com.example.demo.util;

import java.security.Key;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;

@Component
public class JwtUtil {

    @Value("${app.secret}")
    private String secret;
    
    private Key key;

    @PostConstruct
    public void init() {
        if (secret.length() < 64) {
            throw new IllegalArgumentException(
              "The secret key must be at least 512 bits (65 characters) long.");
        }
        key = Keys.hmacShaKeyFor(secret.getBytes());
    }

    // generate token
    public String generateToken(String subject) {
        return Jwts.builder()
                .setSubject(subject)
                .setIssuer("KnowProgram-PVT")
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 
                        TimeUnit.MINUTES.toMillis(10)))
                .signWith(key, SignatureAlgorithm.HS512).compact();
    }

    // read claims
    public Claims getClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    // read expire date & time
    public Date getExpDate(String token) {
        return getClaims(token).getExpiration();
    }

    // read subject/username
    public String getUsername(String token) {
        return getClaims(token).getSubject();
    }

    // validate is token expired?
    public boolean isTokenExpired(String token) {
        Date expireDate = getClaims(token).getExpiration();
        return expireDate.before(new Date(System.currentTimeMillis()));
    }

    // validate username in token and database, expDate
    public boolean validateToken(String token, String username) {
        String tokenUserName = getUsername(token);
        return username.equals(tokenUserName) && !isTokenExpired(token);
    }

}
