package ru.inurgalimov.restaurateur.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.inurgalimov.restaurateur.dto.RestaurantResponse;
import ru.inurgalimov.restaurateur.dto.VoteRequest;
import ru.inurgalimov.restaurateur.dto.VoteResponse;
import ru.inurgalimov.restaurateur.entity.VoteEntity;
import ru.inurgalimov.restaurateur.mapper.RestaurantMapper;
import ru.inurgalimov.restaurateur.mapper.VoteMapper;
import ru.inurgalimov.restaurateur.repository.RestaurantRepository;
import ru.inurgalimov.restaurateur.repository.VoteRepository;
import ru.inurgalimov.restaurateur.service.UserService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private static final LocalTime FIX_TIME = LocalTime.of(11, 00);
    private final VoteRepository voteRepository;
    private final VoteMapper voteMapper;
    private final RestaurantRepository restaurantRepository;
    private final RestaurantMapper restaurantMapper;

    @Override
    public UUID toVote(VoteRequest request, UUID userId) {
        Optional<VoteEntity> byUserId = voteRepository.findByUserId(userId);
        UUID result =  byUserId
                .map(VoteEntity::getCreateDate)
                .map(dateTime -> LocalDateTime.ofInstant(dateTime, ZoneId.systemDefault()))
                .map(dateTime -> {
                    LocalDateTime currenDateTime = LocalDateTime.now();
                    if (currenDateTime.toLocalDate().isAfter(dateTime.toLocalDate())) {
                        VoteEntity entity = voteMapper.toEntity(request, userId);
                        entity.setUuid(byUserId.map(VoteEntity::getUuid).orElse(null));
                        return voteRepository.save(entity).getUuid();
                    }
                    if (currenDateTime.toLocalTime().isBefore(FIX_TIME)) {
                        VoteEntity entity = voteMapper.toEntity(request, userId);
                        entity.setUuid(byUserId.map(VoteEntity::getUuid).orElse(null));
                        return voteRepository.save(entity).getUuid();
                    }
                    return UUID.fromString("00000000-0000-0000-0000-000000000000");
                }).orElse(null);
        if (Objects.isNull(result)) {
            return voteRepository.save(voteMapper.toEntity(request, userId)).getUuid();
        }
        return result;
    }

    @Override
    public Map<RestaurantResponse, Long> getTopRestaurants() {
        LocalDate date = LocalDate.now();
        return voteRepository.findAll()
                .stream()
                .filter(VoteEntity::getVote)
                .filter(entity -> LocalDateTime.ofInstant(entity.getCreateDate(), ZoneId.systemDefault())
                        .toLocalDate()
                        .equals(date))
                .map(voteMapper::toDto)
                        .collect(Collectors.groupingBy(VoteResponse::getRestaurant, Collectors.counting()));
    }

    @Override
    public List<RestaurantResponse> getRestaurants() {
        return restaurantRepository.findAll().stream().map(restaurantMapper::toDto).collect(Collectors.toList());
    }

}
