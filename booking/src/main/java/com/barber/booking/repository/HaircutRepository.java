package com.barber.booking.repository;

import com.barber.booking.domain.Haircut;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface HaircutRepository extends JpaRepository<Haircut,Integer> {

    Optional<Haircut> findByType(String type);
}
