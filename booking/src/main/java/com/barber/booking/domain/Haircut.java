package com.barber.booking.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@Setter
@Entity
public class Haircut {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer haircutId;
    private String type;
    private Integer price;
    private Integer duration;
}
