package com.bfv.reservation.model.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity @Table(name = "car")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class Car extends PersistentEntity {
    @Id
    private String id;
    private String brand;
    private String plate;
    private String motor;
    private String color;
}
