package com.bfv.reservation.model.airport;

import com.bfv.reservation.model.PersistentEntity;
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
public class Aircraft extends PersistentEntity {
    private String name;
    private String code;
}
