package com.barber.booking.service.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BarberRequest {

    private Long barberId;
    private String name;
}
