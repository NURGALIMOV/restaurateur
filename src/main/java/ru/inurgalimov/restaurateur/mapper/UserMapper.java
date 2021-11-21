package ru.inurgalimov.restaurateur.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.inurgalimov.restaurateur.dto.AuthRequest;
import ru.inurgalimov.restaurateur.entity.UserEntity;
import ru.inurgalimov.restaurateur.security.userdetails.UserAccount;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "isAccountNonExpired", ignore = true)
    @Mapping(target = "isAccountNonLocked", ignore = true)
    @Mapping(target = "isCredentialsNonExpired", ignore = true)
    @Mapping(target = "isEnabled", ignore = true)
    @Mapping(target = "token", ignore = true)
    @Mapping(target = "authorities", ignore = true)
    UserAccount toDto(UserEntity entity);

    @Mapping(target = "uuid", ignore = true)
    @Mapping(target = "createDate", ignore = true)
    @Mapping(target = "role", ignore = true)
    UserEntity toEntity(AuthRequest request);

}
