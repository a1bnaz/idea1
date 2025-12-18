package com.idea1.app.backend.service;

import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Date;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

import org.springframework.stereotype.Service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Service
// service that handles JWT token generation
// role: token generator
// what it does: creates JWT tokens with a subject (username), issued at time, expiration time, and signs it with a secret key
// connection: used by UserService to generate tokens upon successful authentication
public class JWTService {
    private String SECRET = "";
    private static final long EXP_MS = 1000 * 60 * 60; // 1 hour

    // everytime you restart the app, a new secret key is generated... meaning a new token is generated... invalidating all previous tokens - not ideal for production. store the secret key securely in application.properties or an environment variable for production use.
    public JWTService() throws NoSuchAlgorithmException {
        try {
            KeyGenerator keyGen = KeyGenerator.getInstance("HmacSHA256");
            SecretKey secretKey = keyGen.generateKey();
            SECRET = Base64.getEncoder().encodeToString(secretKey.getEncoded());
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public String generateToken(String subject) {
        Date now = new Date();
        Date exp = new Date(now.getTime() + EXP_MS);
        return Jwts.builder()
                .subject(subject)
                .issuedAt(now)
                .expiration(exp)
                .signWith(Keys.hmacShaKeyFor(SECRET.getBytes(StandardCharsets.UTF_8)))
                .compact();
    }
}