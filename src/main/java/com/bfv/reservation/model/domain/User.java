package com.bfv.reservation.model.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "[user]")
@Getter
@Setter
public class User extends PersistentEntity {

    private String email;
    private String firstName;
    private String lastName;

    @Column(length = 500)
    private String password;
    private String role = "CUSTOMER";
}
