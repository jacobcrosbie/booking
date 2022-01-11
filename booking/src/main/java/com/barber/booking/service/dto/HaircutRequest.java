package com.barber.booking.service.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HaircutRequest {

    private String type;
    private Integer price;
    private Integer duration;
}
