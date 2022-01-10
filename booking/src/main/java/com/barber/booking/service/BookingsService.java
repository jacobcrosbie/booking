package com.barber.booking.service;
import com.barber.booking.domain.Bookings;
import com.barber.booking.service.dto.BookingsResponse;

import java.util.List;

public interface BookingsService {

    List<BookingsResponse> findByUserId(Long user);

    List<Bookings> findAll();
}
