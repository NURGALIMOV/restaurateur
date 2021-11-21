package ru.inurgalimov.restaurateur.service;

import ru.inurgalimov.restaurateur.dto.RestaurantResponse;
import ru.inurgalimov.restaurateur.dto.VoteRequest;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public interface UserService {

    UUID toVote(VoteRequest request, UUID userId);
    Map<RestaurantResponse, Long> getTopRestaurants();
    public List<RestaurantResponse> getRestaurants();

}
