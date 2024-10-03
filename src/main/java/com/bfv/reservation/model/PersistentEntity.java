package com.bfv.reservation.model;

import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import org.hibernate.annotations.UuidGenerator;

@MappedSuperclass
public class PersistentEntity {
    @Id
    @UuidGenerator
    private String id;
}
