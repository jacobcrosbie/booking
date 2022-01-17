package com.barber.booking.service.impl;

import com.barber.booking.domain.Barber;
import com.barber.booking.domain.Bookings;
import com.barber.booking.domain.Haircut;
import com.barber.booking.repository.BarberRepository;
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

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookingsServiceImpl implements BookingsService {

    private final BookingsRepository bookingsRepository;

    private final BarberRepository barberRepository;

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

    public List<Bookings> findAll() {
        return bookingsRepository.findAll();
    }

    public List<Barber> findAllBarbers(){
        return barberRepository.findAll();
    }

    public ArrayList<String[]> findByBarberAndDate(Long barberId, String dateTime) {
        // user has selected a barber they want and a date
        // (not time yet - this function will show available times for that date and let user select from those)
        // now we return this barbers available time slots for the given date

        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime localDateTime = LocalDateTime.parse(dateTime, dateFormat);
        LocalDateTime startDateTime = localDateTime;
        LocalDateTime endDateTime = localDateTime.plusHours(24 - Integer.parseInt(dateTime.substring(11, 13))).minusMinutes(Integer.parseInt(dateTime.substring(14)));
        List<Bookings> barberBookings = bookingsRepository.findByBarberAndDate(barberId, startDateTime, endDateTime);

        ArrayList<String[]> localDateTimes = new ArrayList();

        // this returns booking times
        /*for(int i = 0; i < barberBookings.size(); i++){
            String[] times = new String[2];
            times[0] = barberBookings.get(i).getStartTime().toString().substring(11);
            times[1] = barberBookings.get(i).getEndTime().toString().substring(11);
        }*/

        //returns free slots
        for (int i = 0; i < barberBookings.size(); i++) {
            String[] times = new String[2];
            LocalDateTime bookingTimeStart = barberBookings.get(i).getStartTime();
            LocalDateTime bookingTimeEnd = barberBookings.get(i).getEndTime();
            if (i == 0) {
                Duration diffBetweenTimes = Duration.between(LocalDateTime.parse(bookingTimeStart.toString().substring(0, 10) + "T08:00"), bookingTimeStart);
                if (diffBetweenTimes.toMinutes() > 0) {
                    // 8am + minutes free
                    times[0] = LocalDateTime.parse(bookingTimeStart.toString().substring(0, 10) + "T08:00").toString().substring(11);
                    // will be free from 8am to the start of the first haircut
                    times[1] = barberBookings.get(i).getStartTime().toString().substring(11);
                    localDateTimes.add(times);

                }
                // if there is no difference then haircut is at 8am so no free slots here
                //if there is only one booking - need to do the final time slot here
                if (barberBookings.size() == 1) {
                    String[] times2 = new String[2];
                    times2[0] = barberBookings.get(i).getEndTime().toString().substring(11);
                    // will be free from 8am to the start of the first haircut
                    times2[1] =  LocalDateTime.parse(bookingTimeStart.toString().substring(0, 10) + "T17:00").toString().substring(11);
                    localDateTimes.add(times2);

                }
            }
            // now we have checked first booking
            // we now need to compare current booking START time to previous booking END time to get next available time slot
            else {
                // if we are at our last booking, we need to check whether if there are any free slots until 5pm
                if (i == barberBookings.size() - 1) {
                    Duration diffBetweenTimes = Duration.between(bookingTimeEnd, LocalDateTime.parse(bookingTimeStart.toString().substring(0, 10) + "T17:00"));
                    if (diffBetweenTimes.toMinutes() > 0) {
                        // endtime + minutes free
                        times[0] = barberBookings.get(i).getEndTime().toString().substring(11);
                        // will be free from endtime to 5pm
                        times[1] = LocalDateTime.parse(bookingTimeStart.toString().substring(0, 10) + "T17:00").toString().substring(11);
                        localDateTimes.add(times);
                    }
                } else {
                    // compare this booking to previous
                    Duration diffBetweenTimes = Duration.between(barberBookings.get(i - 1).getEndTime(), bookingTimeStart);
                    if (diffBetweenTimes.toMinutes() > 0) {
                        // previous haircut end time + minutes free
                        times[0] = barberBookings.get(i - 1).getEndTime().toString().substring(11);
                        // will be free from previous haircut end time to the start of the next haircut
                        times[1] = barberBookings.get(i).getStartTime().toString().substring(11);
                        localDateTimes.add(times);
                    }
                    // if there is no difference then haircut is at 8am so no free slots here
                }

            }
        }

        return localDateTimes;
    }

    public BookingsResponse bookHaircut(BookingsRequest bookingsRequest) {


        //useing model mapper here problem with string to date - so just do manually convert string to localdatetime
        ModelMapper modelMapper = new ModelMapper();
        //Bookings booking = modelMapper.map(bookingsRequest, Bookings.class);
        //booking = bookingsRepository.save(booking);
        //System.out.println("New Booking id: " + booking.getBookingId());
        //BookingsResponse bookingsResponse = modelMapper.map(booking, BookingsResponse.class);

        Bookings booking = new Bookings();

        booking.setUserId(bookingsRequest.getUserId());
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        System.out.println(bookingsRequest.getStartTime());
        LocalDateTime a = LocalDateTime.parse(bookingsRequest.getStartTime(), dateFormat);
        System.out.println("LocalDateTime in service as LocalDateTime " + a);
        booking.setStartTime(a);
        booking.setEndTime(a.plusMinutes(bookingsRequest.getHaircut().getDuration()));

        booking.setHaircut(modelMapper.map(bookingsRequest.getHaircut(), Haircut.class));
        booking.setBarber(modelMapper.map(bookingsRequest.getBarber(), Barber.class));

        booking = bookingsRepository.save(booking);
        System.out.println("New Booking id: " + booking.getBookingId());

        BookingsResponse bookingsResponse = modelMapper.map(booking, BookingsResponse.class);

        return bookingsResponse;
    }
}