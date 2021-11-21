package ru.inurgalimov.restaurateur.dto;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.util.List;
import java.util.UUID;

@Data
@ApiModel(value = "Меню")
@SuperBuilder
public class MenuRequest {

    private UUID restaurantId;
    private String name;
    private Boolean isActual;
    private List<Dish> dishes;

}
