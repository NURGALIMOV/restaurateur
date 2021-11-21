package ru.inurgalimov.restaurateur.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiParam;
import lombok.Data;

@Data
@ApiModel(value = "Запрос на аутентификацию")
public class AuthRequest {

    @ApiParam(value = "Логин", required = true)
    private String login;

    @ApiParam(value = "Пароль", required = true)
    private String password;

    @ApiParam(value = "ФИО", required = true)
    private String fullName;

}
