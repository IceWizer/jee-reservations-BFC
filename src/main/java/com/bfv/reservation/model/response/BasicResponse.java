package com.bfv.reservation.model.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class BasicResponse {
    private String message;
    private String id;
}
