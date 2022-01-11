package com.barber.booking.service.dto;


import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
public class BookingsResponse {

    private Long bookingId;
    private BarberResponse barber;
    private HaircutResponse haircut;
    private Date startTime;
    private Date endTime;

}
