package com.barber.booking.repository;

import com.barber.booking.domain.Bookings;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingsRepository extends JpaRepository<Bookings, Long> {

    @Query(value = "SELECT b FROM Bookings b WHERE b.userId =:user")
    List<Bookings> findByUserId(@Param("user") Long user);

    @Query(value = "SELECT b FROM Bookings b WHERE b.barber =:barberId")
    List<Bookings> findByBarberAndDate(@Param("barberId") Long barberId);

}
