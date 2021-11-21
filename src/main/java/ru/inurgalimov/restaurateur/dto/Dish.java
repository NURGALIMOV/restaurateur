package ru.inurgalimov.restaurateur.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiParam;
import lombok.Data;

@Data
@ApiModel(value = "Блюдо")
public class Dish {

    @ApiParam(value = "Наименование", required = true)
    private String name;
    @ApiParam(value = "Стоимость", required = true)
    private Float cost;

}
