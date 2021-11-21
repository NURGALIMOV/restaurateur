package ru.inurgalimov.restaurateur.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import ru.inurgalimov.restaurateur.dto.AuthRequest;

import javax.validation.constraints.NotNull;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Api(tags = "auth", value = "API для авторизации в системе")
@RequestMapping("/api/v1/auth")
public interface AuthController {

    @ApiOperation(value = "Регистрация пользователя", nickname = "registration", response = String.class)
    @PostMapping(value = "/registration", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    String registration(@NotNull
                        @ApiParam(value = "Данные для регистрации", required = true)
                        @RequestBody AuthRequest request);

    @ApiOperation(value = "Аутентификация пользователя в системе", nickname = "login", response = String.class)
    @PostMapping(value = "/login", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    String login(@NotNull
                 @ApiParam(value = "Данные для аутентификации пользователя в система", required = true)
                 @RequestBody AuthRequest request);

}
