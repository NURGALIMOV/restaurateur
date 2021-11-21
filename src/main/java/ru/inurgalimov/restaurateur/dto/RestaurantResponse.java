package ru.inurgalimov.restaurateur.dto;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiParam;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
@ApiModel(value = "Ресторан")
public class RestaurantResponse {

    @ApiParam(value = "Идентификатор")
    private UUID id;
    @ApiParam(value = "Наименование")
    private String name;
    @ApiParam(value = "Адрес")
    private String address;
    @ApiParam(value = "Меню")
    private MenuResponse menu;

}
