package ru.inurgalimov.restaurateur.entity;


import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

@Entity
@Setter
@Getter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class UserEntity extends AbstractEntity {

    private String fullName;
    private String login;
    private String password;
    @Enumerated(EnumType.STRING)
    @Builder.Default
    private Role role = Role.USER;

}
