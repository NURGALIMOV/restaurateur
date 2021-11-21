package ru.inurgalimov.restaurateur.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;
import ru.inurgalimov.restaurateur.entity.VoteEntity;

import java.util.Optional;
import java.util.UUID;

public interface VoteRepository extends JpaRepository<VoteEntity, UUID> {

    @Transactional
    Optional<VoteEntity> findByUserId(UUID userId);


}
