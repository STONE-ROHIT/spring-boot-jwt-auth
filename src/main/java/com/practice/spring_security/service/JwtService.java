package com.practice.spring_security.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService{

    private final String secretKey;

    public JwtService() {
        secretKey = generateSecretKey();
    }

    public String generateSecretKey(){
        try{
            KeyGenerator keyGen = KeyGenerator.getInstance("HmacSHA256");
            SecretKey secretKey = keyGen.generateKey();
            String encodedKey = Base64.getEncoder().encodeToString(secretKey.getEncoded());
            System.out.println("Secret Key: " + encodedKey);
            return encodedKey;
        }catch(NoSuchAlgorithmException e){
            throw new RuntimeException(e);
        }
    }

    public String generateToken(String username){
        Map<String, Object> claims = new HashMap<>();

        return Jwts.builder()
                .claims(claims)
                .subject(username)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 3 )) // 30 minutes
                .signWith(getKey(), SignatureAlgorithm.HS256)
                .compact();
    }
// 1000ms * 60 -> 1min *3  -> 3 mins

    private Key getKey(){
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String extractUserName(String jwToken){
        return extractClaims(jwToken, Claims::getSubject);
    }
    private <T> T extractClaims(String token, Function<Claims, T> claimResolver){
        final Claims claims = extractAllClaims(token);
        return claimResolver.apply(claims);
    }

    private Claims extractAllClaims(String token){
        return Jwts.parser()
                .setSigningKey(getKey()) // Use setSigningKey instead of verifyWith
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public boolean validateToken(String jwToken, UserDetails userDetail){
        final String username = extractUserName(jwToken);
        return (username.equals(userDetail.getUsername()) && !isTokenExpired(jwToken));
    }

    private boolean isTokenExpired(String token){
        Date expiration = extractClaims(token, Claims::getExpiration);
        return expiration.before(new Date());
    }

}
