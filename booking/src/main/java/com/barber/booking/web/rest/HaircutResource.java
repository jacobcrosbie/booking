package com.barber.booking.web.rest;

import com.barber.booking.domain.Haircut;
import com.barber.booking.repository.HaircutRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class HaircutResource {

    @Autowired
    public HaircutRepository haircutRepository;

    @GetMapping("/haircut")
    public List<Haircut> findAll(){
        return haircutRepository.findAll();
    }
}
