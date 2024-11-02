package com.bfv.reservation.model.domain;

import com.bfv.reservation.model.domain.location.City;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Hotel extends PersistentEntity {

    private String name;
    private String address;

    @ManyToOne
    private City city;
    private String phoneNumber;
    private String email;
    private String website;

    @Column(length = 2000)
    private String description;
}
