package com.bfv.reservation.model.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import java.util.List;

@Getter
@Setter
public class HotelRequest {
    @NotBlank
    @Length(min = 5, max = 50)
    private String name;

    @NotBlank
    private String address;

    @NotBlank
    private String phoneNumber;

    @NotBlank
    private String email;

    @NotBlank
    private String website;

    private double rating;

    private List<RoomRequest> rooms;

    @NotBlank
    private String cityId;
}