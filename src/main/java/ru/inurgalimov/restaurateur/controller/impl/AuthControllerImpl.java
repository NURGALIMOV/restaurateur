package ru.inurgalimov.restaurateur.controller.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import ru.inurgalimov.restaurateur.controller.AuthController;
import ru.inurgalimov.restaurateur.dto.AuthRequest;
import ru.inurgalimov.restaurateur.service.AuthService;

@RestController
@RequiredArgsConstructor
public class AuthControllerImpl implements AuthController {

    private final AuthService authService;

    @Override
    public String registration(AuthRequest request) {
        return authService.registration(request);
    }

    @Override
    public String login(AuthRequest request) {
        return authService.login(request);
    }

}
