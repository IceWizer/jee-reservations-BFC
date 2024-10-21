package com.bfv.reservation.model;

import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.UuidGenerator;

@MappedSuperclass
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class PersistentEntity {
    @Id
    @UuidGenerator
    private String id;
}
