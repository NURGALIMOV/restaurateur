package ru.inurgalimov.restaurateur.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.inurgalimov.restaurateur.dto.RestaurantResponse;
import ru.inurgalimov.restaurateur.dto.VoteRequest;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Api(tags = "user", value = "API для user")
@RequestMapping("/api/v1/user")
public interface UserController {

    @ApiOperation(value = "Голосовать", nickname = "vote", response = UUID.class)
    @PostMapping(value = "/vote", consumes = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    UUID toVote(@NotNull
                @ApiParam(value = "Голос", required = true)
                @RequestBody VoteRequest request,
                @RequestHeader(name = "Authorization") String token);

    @ApiOperation(value = "Получить список ресторанов", nickname = "get-restaurants-user", response = Map.class)
    @GetMapping(value = "/top-restaurants", produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    Map<RestaurantResponse, Long> getTopRestaurants(@RequestHeader(name = "Authorization") String token);

    @ApiOperation(value = "Получить список ресторанов", nickname = "get-restaurants-user", response = Map.class)
    @GetMapping(value = "/restaurants", produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    List<RestaurantResponse> getRestaurants(@RequestHeader(name = "Authorization") String token);

}
