package ru.inurgalimov.restaurateur.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiParam;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.UUID;

@ApiModel(value = "Меню")
@Setter
@Getter
@SuperBuilder
public class MenuResponse extends MenuRequest {

    @ApiParam(value = "Идентификатор")
    private UUID id;

}
