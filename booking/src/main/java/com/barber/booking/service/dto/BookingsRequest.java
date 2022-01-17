package com.barber.booking.service.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookingsRequest {

    private Long bookingId;
    private Long userId;
    private BarberResponse barber;
    private HaircutResponse haircut;
    private String startTime;
    private String endTime;

    @Override
    public String toString() {
        return "BookingsRequest{" +
                "bookingId=" + bookingId +
                ", barber=" + barber +
                ", haircut=" + haircut +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                '}';
    }
}
