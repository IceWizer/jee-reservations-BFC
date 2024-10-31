package com.bfv.reservation.model.domain.car;

import com.bfv.reservation.model.domain.PersistentEntity;
import jakarta.persistence.Entity;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class Category extends PersistentEntity {
    private String name;
}
