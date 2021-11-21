package ru.inurgalimov.restaurateur.service;

import ru.inurgalimov.restaurateur.dto.AuthRequest;

public interface AuthService {

    String registration(AuthRequest request);
    String login(AuthRequest request);

}
