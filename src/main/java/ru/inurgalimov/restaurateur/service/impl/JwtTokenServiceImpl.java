package ru.inurgalimov.restaurateur.service.impl;

import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.inurgalimov.restaurateur.mapper.UserMapper;
import ru.inurgalimov.restaurateur.provider.JwtTokenProvider;
import ru.inurgalimov.restaurateur.repository.UserRepository;
import ru.inurgalimov.restaurateur.security.userdetails.UserAccount;
import ru.inurgalimov.restaurateur.service.JwtTokenService;

@RequiredArgsConstructor
@Service
public class JwtTokenServiceImpl implements JwtTokenService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final JwtTokenProvider jwtTokenProvider;

    @Override
    public UserAccount userInfoByToken(String token) {
        Claims claims = jwtTokenProvider.parseToken(token);
        String subject = claims.getSubject();
        return userRepository.findByLogin(subject).map(userMapper::toDto)
                .orElseThrow(RuntimeException::new);
    }
}
