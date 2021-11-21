package ru.inurgalimov.restaurateur.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.inurgalimov.restaurateur.entity.MenuEntity;

import java.util.Optional;
import java.util.UUID;

public interface MenuRepository extends JpaRepository<MenuEntity, UUID> {

    Optional<MenuEntity> findByRestaurantIdAndIsActualTrue(UUID id);

}
