package ru.inurgalimov.restaurateur.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.inurgalimov.restaurateur.dto.MenuRequest;
import ru.inurgalimov.restaurateur.dto.MenuResponse;
import ru.inurgalimov.restaurateur.dto.RestaurantRequest;
import ru.inurgalimov.restaurateur.dto.RestaurantResponse;
import ru.inurgalimov.restaurateur.entity.MenuEntity;
import ru.inurgalimov.restaurateur.entity.RestaurantEntity;
import ru.inurgalimov.restaurateur.mapper.MenuMapper;
import ru.inurgalimov.restaurateur.mapper.RestaurantMapper;
import ru.inurgalimov.restaurateur.repository.MenuRepository;
import ru.inurgalimov.restaurateur.repository.RestaurantRepository;
import ru.inurgalimov.restaurateur.service.AdminService;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {

    private final RestaurantRepository restaurantRepository;
    private final MenuRepository menuRepository;
    private final RestaurantMapper restaurantMapper;
    private final MenuMapper menuMapper;

    @Override
    public UUID createRestaurant(RestaurantRequest request) {
        return restaurantRepository.save(restaurantMapper.toEntity(request)).getUuid();
    }

    @Override
    public RestaurantResponse getRestaurant(UUID restaurantId) {
        return restaurantRepository.findById(restaurantId)
                .map(restaurantMapper::toDto)
                .orElseThrow(() -> new RuntimeException("NotFound restaurant"));
    }

    @Override
    public List<RestaurantResponse> getRestaurants() {
        return restaurantRepository.findAll()
                .stream()
                .map(restaurantMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public UUID createMenu(MenuRequest request) {
        request.setIsActual(true);
        menuRepository.findByRestaurantIdAndIsActualTrue(request.getRestaurantId()).ifPresent(m -> {
            m.setIsActual(false);
            menuRepository.save(m);
        });
        MenuEntity entity = menuRepository.save(menuMapper.toEntityWithDishes(request));
        RestaurantEntity restaurant = restaurantRepository.findById(request.getRestaurantId()).orElseThrow(RuntimeException::new);
        restaurant.setActualMenu(entity);
        restaurantRepository.save(restaurant);
        return entity.getUuid();
    }

    @Override
    @Transactional
    public MenuResponse getMenu(UUID menuId) {
        return menuMapper.toDto(menuRepository.getById(menuId));
    }
}
