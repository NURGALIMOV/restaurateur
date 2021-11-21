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
import java.util.UUID;

@Entity
@Setter
@Getter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "vote")
public class VoteEntity extends AbstractEntity {

    private Boolean vote;

    @OneToOne
    @JoinColumn(name = "restaurantId", nullable = false)
    private RestaurantEntity restaurant;
    private UUID menuId;
    private UUID userId;

}
