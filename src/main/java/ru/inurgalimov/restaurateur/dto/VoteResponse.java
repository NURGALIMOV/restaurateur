package ru.inurgalimov.restaurateur.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiParam;
import lombok.Data;

@Data
@ApiModel(value = "Голос")
public class VoteResponse {

    @ApiParam(value = "Голос")
    private boolean vote;
    @ApiParam(value = "Ресторана")
    private RestaurantResponse restaurant;

}
