package com.bfv.reservation.model.domain;

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
public class User extends PersistentEntity {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String phoneNumber;

    private boolean admin = false;
}
