package ru.inurgalimov.restaurateur.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.inurgalimov.restaurateur.dto.AuthRequest;
import ru.inurgalimov.restaurateur.entity.UserEntity;
import ru.inurgalimov.restaurateur.mapper.UserMapper;
import ru.inurgalimov.restaurateur.provider.JwtTokenProvider;
import ru.inurgalimov.restaurateur.repository.UserRepository;
import ru.inurgalimov.restaurateur.security.userdetails.UserAccount;
import ru.inurgalimov.restaurateur.service.AuthService;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository repository;
    private final UserMapper mapper;
    private final PasswordEncoder encoder;
    private final JwtTokenProvider jwtTokenProvider;

    @Override
    @Transactional
    public String registration(AuthRequest request) {
        UserEntity entity = mapper.toEntity(request);
        String pass = request.getPassword();
        entity.setPassword(encoder.encode(pass));
        entity.setLogin(entity.getLogin().toLowerCase());
        repository.save(entity);
        UserAccount account = mapper.toDto(repository.findByLogin(request.getLogin().toLowerCase()).orElse(null));
        Map<String, Object> data = Map.of("login", account.getLogin(),
                "fullName", account.getFullName(),
                "role", account.getRole(),
                "uuid", account.getUuid());
        return jwtTokenProvider.generateToken(request.getLogin(), data);
    }

    @Override
    @Transactional
    public String login(AuthRequest request) {
        UserAccount account = mapper.toDto(repository.findByLogin(request.getLogin().toLowerCase()).orElseThrow(RuntimeException::new));
        encoder.matches(request.getPassword(), account.getPassword());
        Map<String, Object> data = Map.of("login", account.getLogin(),
                "fullName", account.getFullName(),
                "role", account.getRole(),
                "uuid", account.getUuid());
        return jwtTokenProvider.generateToken(request.getLogin(), data);
    }

}
