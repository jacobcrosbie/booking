package com.barber.booking.service.dto;

import com.barber.booking.utility.BookingEnums;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class BookingsResponse {

    private Long id;
    private String barber;
    private BookingEnums.HaircutTypeEnum type;
    private Date startDate;

}
