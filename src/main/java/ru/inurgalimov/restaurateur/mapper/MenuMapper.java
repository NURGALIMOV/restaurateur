package ru.inurgalimov.restaurateur.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.inurgalimov.restaurateur.dto.MenuRequest;
import ru.inurgalimov.restaurateur.dto.MenuResponse;
import ru.inurgalimov.restaurateur.entity.MenuEntity;

@Mapper(componentModel = "spring", uses = DishMapper.class)
public interface MenuMapper {

    MenuResponse toDto(MenuEntity entity);

    @Mapping(target = "uuid", ignore = true)
    @Mapping(target = "createDate", ignore = true)
    MenuEntity toEntity(MenuRequest request);

    default MenuEntity toEntityWithDishes(MenuRequest request) {
        MenuEntity entity = toEntity(request);
        entity.getDishes().forEach(dish -> dish.setMenu(entity));
        return entity;
    }

}
