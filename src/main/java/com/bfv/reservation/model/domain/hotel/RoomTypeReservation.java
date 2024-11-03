package com.bfv.reservation.model.domain.hotel;

import java.time.LocalDate;

import com.bfv.reservation.model.domain.PersistentEntity;
import com.bfv.reservation.model.domain.User;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class RoomTypeReservation extends PersistentEntity {

    @ManyToOne
    RoomType roomType;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    User user;

    LocalDate fromDate;
    LocalDate toDate;
    String state = "pending";
}
