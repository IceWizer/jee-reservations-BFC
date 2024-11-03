package com.bfv.reservation.model.domain.car;

import com.bfv.reservation.model.domain.PersistentEntity;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Car extends PersistentEntity {
    private String brand;
    private String plate;
    private String motor;
    private String color;

    private double price;

    private boolean available = true;
}
