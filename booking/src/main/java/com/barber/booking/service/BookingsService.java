package com.barber.booking.service;
import com.barber.booking.domain.Barber;
import com.barber.booking.domain.Bookings;
import com.barber.booking.service.dto.BookingsRequest;
import com.barber.booking.service.dto.BookingsResponse;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public interface BookingsService {

    List<BookingsResponse> findByUserId(Long user);

    List<Bookings> findAll();

    List<Barber> findAllBarbers();

    ArrayList<String[]> findByBarberAndDate(Long barberId, String dateTime);

    BookingsResponse bookHaircut(BookingsRequest bookingsRequest);
}
