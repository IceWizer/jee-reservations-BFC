package com.bfv.reservation.model.airport;

import com.bfv.reservation.model.PersistentEntity;
import com.bfv.reservation.model.location.City;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class Airport extends PersistentEntity {
    private String name;
    private String iata;
    private String icao;
    private double lat;
    private double lon;
    private int alt;
    private int size;

    @ManyToOne
    private City city;
}
