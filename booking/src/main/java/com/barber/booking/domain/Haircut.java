package com.barber.booking.domain;


import com.barber.booking.utility.HaircutEnums;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class Haircut {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer haircutId;
    @Enumerated(EnumType.STRING)
    private HaircutEnums type;
    private Integer price;
    private Integer duration;
}
