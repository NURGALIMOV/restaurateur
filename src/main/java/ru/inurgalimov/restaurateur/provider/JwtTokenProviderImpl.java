package ru.inurgalimov.restaurateur.provider;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtTokenProviderImpl implements JwtTokenProvider {

    @Value("${jwt.expiration.mills}")
    private long expirationInMills;
    @Value("${jwt.secret}")
    private String jwtSecret;

    @Override
    public String generateToken(String subject, Map<String, Object> data) {
        Map<String, Object> claims = new HashMap<>(data);
        claims.put(Claims.SUBJECT, subject);
        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(Date.from(Instant.now().plusMillis(expirationInMills)))
                .signWith(SignatureAlgorithm.HS512, jwtSecret).compact();
    }

    @Override
    public boolean validateToken(String token, String subject) {
        Claims claims = parseToken(token);
        String subjectFromToken = claims.getSubject();
        Date date = claims.getExpiration();
        return subject.equals(subjectFromToken) && date.after(new Date());
    }

    @Override
    public Claims parseToken(String token) {
        try {
            return Jwts.parser()
                    .setSigningKey(jwtSecret)
                    .parseClaimsJws(token).getBody();
        } catch (ExpiredJwtException e) {
            throw new RuntimeException("Token expired date error");
        }
    }
}
