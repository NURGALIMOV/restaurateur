package ru.inurgalimov.restaurateur.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiParam;

import java.util.UUID;

@ApiModel(value = "Меню")
public class MenuResponse extends MenuRequest {

    @ApiParam(value = "Идентификатор")
    private UUID id;

}
