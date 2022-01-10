package com.barber.booking.domain;

import com.barber.booking.utility.BookingEnums;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;


@Getter
@Setter
@Entity
public class Bookings {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;
    private String barber;
    private BookingEnums.HaircutTypeEnum type;
    private Date startDate;
    private Date endTime;



}
