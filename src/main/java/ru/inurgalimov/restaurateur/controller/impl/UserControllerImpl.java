package ru.inurgalimov.restaurateur.controller.impl;

import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.HttpMediaTypeException;
import org.springframework.web.bind.annotation.RestController;
import ru.inurgalimov.restaurateur.controller.UserController;
import ru.inurgalimov.restaurateur.dto.RestaurantResponse;
import ru.inurgalimov.restaurateur.dto.VoteRequest;
import ru.inurgalimov.restaurateur.entity.Role;
import ru.inurgalimov.restaurateur.provider.JwtTokenProvider;
import ru.inurgalimov.restaurateur.service.UserService;
import ru.inurgalimov.restaurateur.utils.HttpRequestUtil;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class UserControllerImpl implements UserController {

    private final UserService userService;
    private final JwtTokenProvider jwtTokenProvider;

    @Override
    public UUID toVote(VoteRequest request, String token) {
        Claims claims = jwtTokenProvider.parseToken(HttpRequestUtil.getTokenFromAuthorizationHeader(token));
        Object role = claims.get("role");
        Object uuid = claims.get("uuid");
        if (role.equals(Role.USER.name()) || role.equals(Role.ADMIN.name())) {
            return userService.toVote(request, (UUID) uuid);
        }
        throw new RuntimeException("Нет доступа");
    }

    @Override
    public Map<RestaurantResponse, Long> getTopRestaurants(String token) {
        Claims claims = jwtTokenProvider.parseToken(HttpRequestUtil.getTokenFromAuthorizationHeader(token));
        Object role = claims.get("role");
        if (role.equals(Role.USER.name()) || role.equals(Role.ADMIN.name())) {
            return userService.getTopRestaurants();
        }
        throw new RuntimeException("Нет доступа");
    }

    @Override
    public List<RestaurantResponse> getRestaurants(String token) {
        Claims claims = jwtTokenProvider.parseToken(HttpRequestUtil.getTokenFromAuthorizationHeader(token));
        Object role = claims.get("role");
        if (role.equals(Role.USER.name()) || role.equals(Role.ADMIN.name())) {
            return userService.getRestaurants();
        }
        throw new RuntimeException("Нет доступа");
    }

}
