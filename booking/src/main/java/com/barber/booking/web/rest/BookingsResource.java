package com.barber.booking.web.rest;

import com.barber.booking.domain.Bookings;
import com.barber.booking.repository.BookingsRepository;
import com.barber.booking.service.BookingsService;
import com.barber.booking.service.dto.BookingsResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/bookings")
@RequiredArgsConstructor
public class BookingsResource {

    private final BookingsService bookingsService;

    @Autowired
    public BookingsRepository bookingsRepository;

    @GetMapping("/{user}")
    public List<BookingsResponse> getBookingsForUser(@PathVariable Long user){
        //want to return a json list file of all the bookings made by a user id. should contain barber, day and time
        return bookingsService.findByUserId(user);
    }

    @GetMapping("/all")
    public List<Bookings> findAll(){
        return bookingsRepository.findAll();
    }
}
