package com.bfv.reservation.model.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RoomRequest {
    @NotBlank
    private String roomNumber;

    @NotBlank
    private int capacity;

    @NotBlank
    private double price;
}
