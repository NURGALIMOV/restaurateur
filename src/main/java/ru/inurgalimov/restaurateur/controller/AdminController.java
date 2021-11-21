package ru.inurgalimov.restaurateur.controller;

import io.swagger.annotations.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.inurgalimov.restaurateur.dto.MenuRequest;
import ru.inurgalimov.restaurateur.dto.MenuResponse;
import ru.inurgalimov.restaurateur.dto.RestaurantRequest;
import ru.inurgalimov.restaurateur.dto.RestaurantResponse;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Api(tags = "admin", value = "API для админа")
@RequestMapping("/api/v1/admin")
public interface AdminController {

    @ApiOperation(value = "Создание ресторан", nickname = "create-restaurant", response = UUID.class)
    @PostMapping(value = "/restaurant", consumes = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    UUID createRestaurant(@NotNull
                          @ApiParam(value = "Данные ресторана", required = true)
                          @RequestBody RestaurantRequest request,
                          @RequestHeader(name = "Authorization") String token);

    @ApiOperation(value = "Получить ресторан", nickname = "get-restaurant", response = RestaurantResponse.class)
    @GetMapping(value = "/restaurant/{restaurantId}", produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    RestaurantResponse getRestaurant(@NotNull
                                     @ApiParam(value = "Идентификатор ресторана", required = true)
                                     @PathVariable UUID restaurantId,
                                     @RequestHeader(name = "Authorization") String token);

    @ApiOperation(value = "Получить список ресторанов", nickname = "get-restaurants", response = List.class)
    @GetMapping(value = "/restaurants", produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    List<RestaurantResponse> getRestaurants(@RequestHeader(name = "Authorization") String token);

    @ApiOperation(value = "Создать меню", nickname = "create-menu", response = UUID.class)
    @PostMapping(value = "/menu", consumes = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    UUID createMenu(@NotNull
                    @ApiParam(value = "Меню", required = true)
                    @RequestBody MenuRequest request,
                    @RequestHeader(name = "Authorization") String token);

    @ApiOperation(value = "Получить меню", nickname = "get-menu", response = MenuResponse.class)
    @GetMapping(value = "/menu/{menuId}", produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    MenuResponse getMenu(@NotNull
                         @ApiParam(value = "Идентификатор меню", required = true)
                         @PathVariable UUID menuId,
                         @RequestHeader(name = "Authorization") String token);





}
