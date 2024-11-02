package com.bfv.reservation.model.domain;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class User extends PersistentEntity {

    private String email;
    private String firstName;
    private String lastName;

    @Column(length = 500)
    private String password;
    private String role = "CUSTOMER";
    private LocalDate verifiedAt;
    private String phoneNumber;
}
