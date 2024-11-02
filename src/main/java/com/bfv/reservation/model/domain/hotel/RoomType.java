package com.bfv.reservation.model.domain.hotel;

import com.bfv.reservation.model.domain.Hotel;
import com.bfv.reservation.model.domain.PersistentEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class RoomType extends PersistentEntity {

    private String name;
    private String description;
    private int number;
    private double price;

    @ManyToOne
    private Hotel hotel;
}
