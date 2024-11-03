package com.bfv.reservation.model.domain.hotel;

import com.bfv.reservation.model.domain.PersistentEntity;
import com.bfv.reservation.model.domain.location.City;
import jakarta.persistence.Column;
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
public class Hotel extends PersistentEntity {
    @Column(length = 50)
    private String name;

    private String address;

    @Column(length = 15)
    private String phoneNumber;

    @Column(length = 70)
    private String email;

    @Column(length = 70)
    private String website;

    private double rating;

    private String description;

    @ManyToOne
    private City city;
}
