package com.bfv.reservation.model.domain.location;

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
public class City extends PersistentEntity {

    private String name;

    @ManyToOne
    private Timezone timezone;

    @ManyToOne
    private Country country;
}
