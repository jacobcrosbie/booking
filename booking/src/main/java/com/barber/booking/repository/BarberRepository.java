package com.barber.booking.repository;

import com.barber.booking.domain.Barber;
import com.barber.booking.domain.Haircut;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BarberRepository extends JpaRepository<Barber,Integer> {
}
