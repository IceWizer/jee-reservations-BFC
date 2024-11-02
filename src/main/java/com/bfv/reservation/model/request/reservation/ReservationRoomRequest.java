package com.bfv.reservation.model.request.reservation;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReservationRoomRequest {
    @NotBlank
    private String roomId;
}
