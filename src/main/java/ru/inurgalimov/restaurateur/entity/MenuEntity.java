package ru.inurgalimov.restaurateur.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.Set;
import java.util.UUID;

@Entity
@Setter
@Getter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "menu")
public class MenuEntity extends AbstractEntity {

    private UUID restaurantId;
    private String name;
    private Boolean isActual;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "menu")
    private Set<DishEntity> dishes;

}
