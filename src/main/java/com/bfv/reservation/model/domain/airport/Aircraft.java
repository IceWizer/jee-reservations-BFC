package com.bfv.reservation.model.domain.airport;

import com.bfv.reservation.model.domain.PersistentEntity;
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
