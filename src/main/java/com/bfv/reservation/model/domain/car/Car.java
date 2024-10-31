package com.bfv.reservation.model.domain.car;

import com.bfv.reservation.model.domain.PersistentEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class Car extends PersistentEntity {
    private String licensePlate;

    private String model;

    private int year;

    private boolean available = false;

    @ManyToOne
    private Brand brand;

    @ManyToOne
    private Category category;
}