package com.barber.booking.service.impl;

import com.barber.booking.domain.Bookings;
import com.barber.booking.repository.BookingsRepository;
import com.barber.booking.service.BookingsService;
import com.barber.booking.service.dto.BarberResponse;
import com.barber.booking.service.dto.BookingsRequest;
import com.barber.booking.service.dto.BookingsResponse;
import com.barber.booking.service.dto.HaircutResponse;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
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

            ModelMapper modelMapper = new ModelMapper();
            BookingsResponse bookingsResponse = modelMapper.map(booking,BookingsResponse.class);
            bookingsResponses.add(bookingsResponse);
        });
        return bookingsResponses;
    }

    public List<Bookings> findAll(){
        return bookingsRepository.findAll();
    }
}
