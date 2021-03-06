package com.barber.booking.service.dto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HaircutResponse {

    private Integer haircutId;
    private String type;
    private Integer price;
    private Integer duration;
}
