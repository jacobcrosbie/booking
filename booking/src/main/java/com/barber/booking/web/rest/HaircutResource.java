package com.barber.booking.web.rest;

import com.barber.booking.domain.Haircut;
import com.barber.booking.repository.HaircutRepository;
import com.barber.booking.service.HaircutService;
import com.barber.booking.service.dto.HaircutRequest;
import com.barber.booking.service.dto.HaircutResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/haircut")
public class HaircutResource {

    private final HaircutService haircutService;

    @PostMapping("/create")
    public HaircutResponse createHaircut(@RequestBody HaircutRequest haircutRequest){
        return haircutService.createHaircut(haircutRequest);

    }
}
