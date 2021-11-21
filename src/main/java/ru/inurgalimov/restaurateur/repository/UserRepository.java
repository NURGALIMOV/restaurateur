package ru.inurgalimov.restaurateur.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.inurgalimov.restaurateur.entity.UserEntity;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<UserEntity, UUID> {

    Optional<UserEntity> findByLogin(String login);

}
