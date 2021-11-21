package ru.inurgalimov.restaurateur.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.inurgalimov.restaurateur.entity.RestaurantEntity;

import java.util.UUID;

public interface RestaurantRepository extends JpaRepository<RestaurantEntity, UUID> {
}
