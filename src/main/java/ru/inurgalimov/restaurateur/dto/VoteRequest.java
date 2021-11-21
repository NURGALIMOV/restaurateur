package ru.inurgalimov.restaurateur.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiParam;
import lombok.Data;

import java.util.UUID;

@Data
@ApiModel(value = "Голос")
public class VoteRequest {

    @ApiParam(value = "Голос", required = true)
    private boolean vote;
    @ApiParam(value = "Идентификатор ресторана", required = true)
    private UUID restaurantId;
    @ApiParam(value = "Идентификатор меню", required = true)
    private UUID menuId;

}
