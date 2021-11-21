package ru.inurgalimov.restaurateur.provider;

import io.jsonwebtoken.Claims;

import java.util.Map;

public interface JwtTokenProvider {

    String generateToken(String subject, Map<String, Object> data);

    boolean validateToken(String token, String subject);

    Claims parseToken(String token);
}
