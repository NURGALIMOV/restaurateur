package ru.inurgalimov.restaurateur.service;

import ru.inurgalimov.restaurateur.dto.MenuRequest;
import ru.inurgalimov.restaurateur.dto.MenuResponse;
import ru.inurgalimov.restaurateur.dto.RestaurantRequest;
import ru.inurgalimov.restaurateur.dto.RestaurantResponse;

import java.util.List;
import java.util.UUID;

public interface AdminService {

    UUID createRestaurant(RestaurantRequest request);
    RestaurantResponse getRestaurant(UUID restaurantId);
    List<RestaurantResponse> getRestaurants();
    UUID createMenu(MenuRequest request);
    MenuResponse getMenu(UUID menuId);

}
