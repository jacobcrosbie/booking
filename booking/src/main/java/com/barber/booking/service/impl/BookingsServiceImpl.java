package com.barber.booking.service.impl;

import com.barber.booking.domain.Bookings;
import com.barber.booking.repository.BookingsRepository;
import com.barber.booking.service.BookingsService;
import com.barber.booking.service.dto.BarberResponse;
import com.barber.booking.service.dto.BookingsRequest;
import com.barber.booking.service.dto.BookingsResponse;
import com.barber.booking.service.dto.HaircutResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookingsServiceImpl implements BookingsService {

    private final BookingsRepository bookingsRepository;

    public List<BookingsResponse> findByUserId(Long user) {
        List<BookingsResponse> bookingsResponses = new ArrayList<>();
        List<Bookings> bookingsForUser = bookingsRepository.findByUserId(user);
        bookingsForUser.forEach(booking -> {
            BookingsResponse bookingsResponse = new BookingsResponse();
            bookingsResponse.setBookingId(booking.getBookingId());

            BarberResponse barberResponse = new BarberResponse();
            barberResponse.setBarberId(booking.getBarber().getBarberId());
            barberResponse.setName(booking.getBarber().getName());
            bookingsResponse.setBarber(barberResponse);

            HaircutResponse haircutResponse = new HaircutResponse();
            haircutResponse.setHaircutId(booking.getHaircut().getHaircutId());
            haircutResponse.setType(booking.getHaircut().getType());
            haircutResponse.setDuration(booking.getHaircut().getDuration());
            haircutResponse.setPrice(booking.getHaircut().getPrice());
            bookingsResponse.setHaircut(haircutResponse);


            bookingsResponse.setStartTime(booking.getStartTime());
            bookingsResponse.setEndTime(booking.getEndTime());

            bookingsResponses.add(bookingsResponse);
        });
        return bookingsResponses;
    }

    public List<Bookings> findAll(){
        return bookingsRepository.findAll();
    }
}
