package com.barber.booking.web.rest;

import com.barber.booking.exceptions.HaircutException;
import com.barber.booking.service.HaircutService;
import com.barber.booking.service.dto.HaircutRequest;
import com.barber.booking.service.dto.HaircutResponse;
import lombok.RequiredArgsConstructor;
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
        return new ResponseEntity<>(haircutService.createHaircut(haircutRequest), HttpStatus.CREATED);
    }

    @GetMapping("/details/{type}")
    public ResponseEntity<HaircutResponse> getHaircutDetails(@PathVariable String type) throws HaircutException {
        return new ResponseEntity<>(haircutService.getHaircutDetails(type), HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<HaircutResponse>> getAllHaircuts() {
        return new ResponseEntity<>(haircutService.getAllHaircuts(), HttpStatus.OK);
    }

    @PutMapping("/details/update")
    public ResponseEntity<HaircutResponse> updateHaircutDetails(@RequestBody HaircutRequest haircutRequest) throws HaircutException {
        return new ResponseEntity<>(haircutService.updateHaircutDetails(haircutRequest),HttpStatus.OK);
    }

    @DeleteMapping("/delete/{type}")
    public ResponseEntity<String> deleteHaircut(@PathVariable String type) throws HaircutException {
        return new ResponseEntity<>(haircutService.deleteHaircut(type),HttpStatus.OK);
    }

}
