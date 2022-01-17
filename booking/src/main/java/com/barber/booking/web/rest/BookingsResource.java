package com.barber.booking.web.rest;

import com.barber.booking.domain.Barber;
import com.barber.booking.domain.Bookings;
import com.barber.booking.repository.BookingsRepository;
import com.barber.booking.service.BookingsService;
import com.barber.booking.service.dto.BarberRequest;
import com.barber.booking.service.dto.BarberResponse;
import com.barber.booking.service.dto.BookingsRequest;
import com.barber.booking.service.dto.BookingsResponse;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/bookings")
@RequiredArgsConstructor
@CrossOrigin("*")
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

    @GetMapping("/all/barbers")
    public ResponseEntity<List<BarberResponse>> findAllBarbers(){
        List<Barber> barberList = bookingsService.findAllBarbers();
        List<BarberResponse> barberResponseList = new ArrayList<>();
        barberList.forEach(barber -> {
            BarberResponse barberResponse = new BarberResponse();
            barberResponse.setBarberId(barber.getBarberId());
            barberResponse.setName(barber.getName());
            barberResponseList.add(barberResponse);
        });
        return new ResponseEntity<>(barberResponseList,HttpStatus.OK);
    }

    @GetMapping("/barber/{barberId}/{dateTime}")
    public ResponseEntity<ArrayList<String[]>> findBarbersBookingsOnThisDate(@PathVariable Long barberId, @PathVariable String dateTime){
        ArrayList<String[]> listBookings = bookingsService.findByBarberAndDate(barberId, dateTime);
        return new ResponseEntity<>(listBookings, HttpStatus.OK);
    }

    @PostMapping("/book")
    public ResponseEntity<BookingsResponse> bookHairCut(@RequestBody BookingsRequest bookingsRequest){
        System.out.println(bookingsRequest.toString());

        BookingsResponse book = bookingsService.bookHaircut(bookingsRequest);
        System.out.println(book.toString());

        // do this stuff in service layer
        //use Model mapper here to make response from request
        BookingsResponse bookingsResponse = new BookingsResponse();
        bookingsResponse.setBookingId(bookingsRequest.getBookingId());
        bookingsResponse.setBarber(bookingsRequest.getBarber());
        bookingsResponse.setHaircut(bookingsRequest.getHaircut());


        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        System.out.println(bookingsRequest.getStartTime());
        LocalDateTime a = LocalDateTime.parse(bookingsRequest.getStartTime(),dateFormat);
        System.out.println(a);
        
        bookingsResponse.setStartTime(a.toString());

        //end time is start time + haircut duration

        int duration = Integer.parseInt(bookingsRequest.getStartTime().substring(14));
        System.out.println(duration);

        System.out.println(a.plusMinutes(duration));

        return new ResponseEntity<>(bookingsResponse, HttpStatus.CREATED);
    }
}
