package com.bfv.reservation.model.domain.flight;

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
public class Airline extends PersistentEntity {
    private String name;
    private String code;
    private String icao;

    @ManyToOne
    public Aircraft aircraft;
}
