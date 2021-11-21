package ru.inurgalimov.restaurateur.controller.impl;

import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import ru.inurgalimov.restaurateur.controller.AdminController;
import ru.inurgalimov.restaurateur.dto.MenuRequest;
import ru.inurgalimov.restaurateur.dto.MenuResponse;
import ru.inurgalimov.restaurateur.dto.RestaurantRequest;
import ru.inurgalimov.restaurateur.dto.RestaurantResponse;
import ru.inurgalimov.restaurateur.entity.Role;
import ru.inurgalimov.restaurateur.provider.JwtTokenProvider;
import ru.inurgalimov.restaurateur.service.AdminService;
import ru.inurgalimov.restaurateur.utils.HttpRequestUtil;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class AdminControllerImpl implements AdminController {

    private final AdminService adminService;
    private final JwtTokenProvider jwtTokenProvider;

    @Override
    public UUID createRestaurant(RestaurantRequest request, String token) {
        Claims claims = jwtTokenProvider.parseToken(HttpRequestUtil.getTokenFromAuthorizationHeader(token));
        Object role = claims.get("role");
        if (role.equals(Role.ADMIN.name())) {
            return adminService.createRestaurant(request);
        }
        throw new RuntimeException("Нет доступа");
    }

    @Override
    public RestaurantResponse getRestaurant(UUID restaurantId, String token) {
        Claims claims = jwtTokenProvider.parseToken(HttpRequestUtil.getTokenFromAuthorizationHeader(token));
        Object role = claims.get("role");
        if (role.equals(Role.ADMIN.name())) {
            return adminService.getRestaurant(restaurantId);
        }
        throw new RuntimeException("Нет доступа");
    }

    @Override
    public List<RestaurantResponse> getRestaurants(String token) {
        Claims claims = jwtTokenProvider.parseToken(HttpRequestUtil.getTokenFromAuthorizationHeader(token));
        Object role = claims.get("role");
        if (role.equals(Role.ADMIN.name())) {
            return adminService.getRestaurants();
        }
        throw new RuntimeException("Нет доступа");
    }

    @Override
    public UUID createMenu(MenuRequest request, String token) {
        Claims claims = jwtTokenProvider.parseToken(HttpRequestUtil.getTokenFromAuthorizationHeader(token));
        Object role = claims.get("role");
        if (role.equals(Role.ADMIN.name())) {
            return adminService.createMenu(request);
        }
        throw new RuntimeException("Нет доступа");
    }

    @Override
    public MenuResponse getMenu(UUID menuId, String token) {
        Claims claims = jwtTokenProvider.parseToken(HttpRequestUtil.getTokenFromAuthorizationHeader(token));
        Object role = claims.get("role");
        if (role.equals(Role.ADMIN.name())) {
            return adminService.getMenu(menuId);
        }
        throw new RuntimeException("Нет доступа");
    }

}
