package com.barber.booking.service.impl;

import com.barber.booking.domain.Bookings;
import com.barber.booking.domain.Haircut;
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

    @Autowired
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


            bookingsResponse.setStartTime(booking.getStartTime().toString());

            bookingsResponses.add(bookingsResponse);
        });
        return bookingsResponses;
    }

    public List<Bookings> findAll(){
        return bookingsRepository.findAll();
    }

    public List<BookingsResponse> findByBarberAndDate(Long barberId, String dateTime){
        List<BookingsResponse> bookingsResponses = new ArrayList<>();
        List<Bookings> barberBookings = bookingsRepository.findByBarberAndDate(barberId);
        barberBookings.forEach(booking -> {
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


            bookingsResponse.setStartTime(booking.getStartTime().toString());

            bookingsResponses.add(bookingsResponse);
        });
        return bookingsResponses;
    }

    public void bookHaircut(BookingsRequest bookingsRequest){
        Bookings bookings = new Bookings();

        //use model mapper
        bookings.setUserId(bookingsRequest.getUserId());
        ModelMapper modelMapper = new ModelMapper();
        Haircut haircut = modelMapper.map(haircutRequest, Haircut.class);
        haircut = haircutRepository.save(haircut);
        //bookings.setBarber(bookingsRequest.getBarber());
        //bookings.getHaircut(bookingsRequest.getHaircut());

    }
}
