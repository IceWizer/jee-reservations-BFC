package com.bfv.reservation.model.domain.location;

import com.bfv.reservation.model.domain.PersistentEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
public class Timezone extends PersistentEntity {

    private String name;

    @Column(name = "time_offset")
    private int offset;
}
