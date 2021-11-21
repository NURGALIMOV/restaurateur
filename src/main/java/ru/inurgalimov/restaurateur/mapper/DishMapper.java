package ru.inurgalimov.restaurateur.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.inurgalimov.restaurateur.dto.Dish;
import ru.inurgalimov.restaurateur.entity.DishEntity;

@Mapper(componentModel = "spring")
public interface DishMapper {

    Dish toDto(DishEntity entity);

    @Mapping(target = "uuid", ignore = true)
    @Mapping(target = "menu", ignore = true)
    @Mapping(target = "createDate", ignore = true)
    DishEntity toEntity(Dish dto);

}
