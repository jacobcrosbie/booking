package com.barber.booking.domain;

import com.barber.booking.utility.HaircutEnums;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;


@Getter
@Setter
@Entity
public class Bookings {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookingId;
    private Long userId;

    @ManyToOne
    @JoinColumn(name ="barber_id")
    private Barber barber;

    @ManyToOne
    @JoinColumn(name = "haircut_id")
    private Haircut haircut;

    private Date startTime;
    private Date endTime;



}
