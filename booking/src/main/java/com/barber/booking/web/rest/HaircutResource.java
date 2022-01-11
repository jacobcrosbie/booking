package com.barber.booking.web.rest;

import com.barber.booking.domain.Haircut;
import com.barber.booking.exceptions.HaircutException;
import com.barber.booking.repository.HaircutRepository;
import com.barber.booking.service.HaircutService;
import com.barber.booking.service.dto.HaircutRequest;
import com.barber.booking.service.dto.HaircutResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/haircut")
public class HaircutResource {

    private final HaircutService haircutService;

    @PostMapping("/create")
    public ResponseEntity<HaircutResponse> createHaircut(@RequestBody HaircutRequest haircutRequest) throws HaircutException {
        return new ResponseEntity<>(haircutService.createHaircut(haircutRequest), HttpStatus.OK);

    }
}
