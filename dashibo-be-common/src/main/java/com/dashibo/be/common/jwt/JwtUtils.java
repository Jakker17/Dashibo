package com.dashibo.be.common.jwt;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtUtils {
    final private String secret = "secret";

    public String generateToken(UserDetails username) {

        long expirationMs = 1000000L;
        return Jwts.builder()
                .setClaims(getClaims(username))
                .setSubject(username.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expirationMs))
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }

    private Map<String, Object> getClaims(UserDetails user) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("Authorities",user.getAuthorities());
        claims.put("Acc-expired",user.isAccountNonExpired());
        return claims;
    }

    public boolean validateToken(String token, UserDetails username) {
        String tokenUsername = extractUsername(token);
        return (tokenUsername.equals(username.getUsername()) && !isTokenExpired(token));
    }

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public Date extractExpirationDate(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    private <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
    }

    private boolean isTokenExpired(String token) {
        return extractExpirationDate(token).toInstant().isBefore(Instant.now());
    }
}