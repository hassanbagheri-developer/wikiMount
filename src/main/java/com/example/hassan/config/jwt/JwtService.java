package com.example.hassan.config.jwt;

import com.example.hassan.exceptions.NotFoundException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class JwtService {

    @Value("${jwt.key}")
    private String key;
    @Value("${jwt.expireDate}")
    private long expireDate;
    private final MessageSource messageSource;


    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("roles", userDetails.getAuthorities());
        claims.put("username", userDetails.getUsername());
        return buildToken(claims, userDetails, expireDate);
    }

    public String buildToken(
            Map<String, Object> claims,
            UserDetails userDetails,
            long expiresIn
    ) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expiresIn))
                .signWith(getSignKey(), SignatureAlgorithm.HS256)
                .compact();

    }

    private Key getSignKey(){
        byte[] keyByte  = Decoders.BASE64.decode(key);
        return Keys.hmacShaKeyFor(keyByte);
    }

    public String extractUserName(String token) {
        Claims claims = parseToken(token);
        return claims.getSubject();
    }

    public boolean isExpire(String token) {
        try {
            Claims claims = parseToken(token);
            Date expiration = claims.getExpiration();
            return expiration.before(new Date());
        } catch (Exception e) {
            return true;
        }
    }

    private Claims parseToken(String token) {
        return Jwts.
                parserBuilder()
                .setSigningKey(getSignKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    // Extract roles from the JWT
    public List<String> extractRoles(String token) {
        Claims claims = extractAllClaims(token);
        List<Map<String, String>> roles = (List<Map<String, String>>) claims.get("roles");
        return roles.stream()
                .map(role -> role.get("authority"))
                .collect(Collectors.toList());
    }
    private Claims extractAllClaims(String token) {
        return Jwts.parser()
                .setSigningKey(key)
                .parseClaimsJws(token)
                .getBody();
    }


}
