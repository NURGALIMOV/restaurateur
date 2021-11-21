package ru.inurgalimov.restaurateur.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Setter
@Getter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "restaurant")
public class RestaurantEntity extends AbstractEntity {

    private String name;
    private String address;
    @OneToOne
    @JoinColumn(name = "actual_menu_id")
    private MenuEntity actualMenu;

}
