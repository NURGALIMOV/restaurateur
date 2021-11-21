package ru.inurgalimov.restaurateur.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.inurgalimov.restaurateur.dto.VoteRequest;
import ru.inurgalimov.restaurateur.dto.VoteResponse;
import ru.inurgalimov.restaurateur.entity.RestaurantEntity;
import ru.inurgalimov.restaurateur.entity.VoteEntity;

import java.util.UUID;

@Mapper(componentModel = "spring", uses = RestaurantMapper.class)
public interface VoteMapper {

    @Mapping(target = "uuid", ignore = true)
    @Mapping(target = "createDate", ignore = true)
    @Mapping(target = "restaurant", ignore = true)
    @Mapping(target = "userId", source = "userId")
    VoteEntity toEntityWithUserId(VoteRequest dto, UUID userId);


    default VoteEntity toEntity(VoteRequest dto, UUID userId) {
        VoteEntity entity = toEntityWithUserId(dto, userId);
        entity.setRestaurant(RestaurantEntity.builder().uuid(dto.getRestaurantId()).build());
        return entity;
    }

    VoteResponse toDto(VoteEntity entity);

}
