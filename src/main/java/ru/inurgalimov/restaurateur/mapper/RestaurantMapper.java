package ru.inurgalimov.restaurateur.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.inurgalimov.restaurateur.dto.RestaurantRequest;
import ru.inurgalimov.restaurateur.dto.RestaurantResponse;
import ru.inurgalimov.restaurateur.entity.RestaurantEntity;

@Mapper(componentModel = "spring", uses = MenuMapper.class)
public interface RestaurantMapper {

    @Mapping(target = "id", source = "uuid")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "address", source = "address")
    @Mapping(target = "menu", source = "actualMenu")
    RestaurantResponse toDto(RestaurantEntity entity);

    @Mapping(target = "uuid", ignore = true)
    @Mapping(target = "name", source = "request.name")
    @Mapping(target = "address", source = "request.address")
    @Mapping(target = "actualMenu", ignore = true)
    @Mapping(target = "createDate", ignore = true)
    RestaurantEntity toEntity(RestaurantRequest request);

}
