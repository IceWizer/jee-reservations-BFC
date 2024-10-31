package com.bfv.reservation.model.domain.hotel;

import com.bfv.reservation.model.domain.PersistentEntity;
import com.bfv.reservation.model.domain.location.City;
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
    private String name;

    private String address;

    private String phoneNumber;

    private String email;

    private String website;

    @ManyToOne
    private City city;
}
