package com.barber.booking.service.dto;

import com.barber.booking.utility.HaircutEnums;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HaircutResponse {

    private Integer haircutId;
    private HaircutEnums type;
    private Integer price;
    private Integer duration;
}
