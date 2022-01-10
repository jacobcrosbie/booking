package com.barber.booking.service.dto;

import com.barber.booking.domain.Barber;
import com.barber.booking.utility.HaircutEnums;
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
    private LocalDateTime startTime;
    private LocalDateTime endTime;

}