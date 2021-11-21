package ru.inurgalimov.restaurateur.service;

import ru.inurgalimov.restaurateur.security.userdetails.UserAccount;

public interface JwtTokenService {

    UserAccount userInfoByToken(String token);
}
