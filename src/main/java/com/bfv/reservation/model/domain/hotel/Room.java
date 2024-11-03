package com.bfv.reservation.model.domain.hotel;

import com.bfv.reservation.model.domain.PersistentEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
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
public class Room extends PersistentEntity {

    private String roomNumber;

    private String name;

    private int capacity;

    private double price;

    private boolean available = true;

    @ManyToOne
    private Hotel hotel;
}
