package ru.inurgalimov.restaurateur.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiParam;
import lombok.Data;

@Data
@ApiModel(value = "Ресторан")
public class RestaurantRequest {

    @ApiParam(value = "Наименование", required = true)
    private String name;
    @ApiParam(value = "Адрес", required = true)
    private String address;

}
