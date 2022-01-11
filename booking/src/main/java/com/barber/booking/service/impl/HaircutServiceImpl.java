package com.barber.booking.service.impl;

import com.barber.booking.domain.Haircut;
import com.barber.booking.exceptions.HaircutException;
import com.barber.booking.repository.HaircutRepository;
import com.barber.booking.service.HaircutService;
import com.barber.booking.service.dto.HaircutRequest;
import com.barber.booking.service.dto.HaircutResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@RequiredArgsConstructor
public class HaircutServiceImpl implements HaircutService {

    private final HaircutRepository haircutRepository;

    public HaircutResponse createHaircut(HaircutRequest haircutRequest) throws HaircutException {

        Optional<Haircut> optional = haircutRepository.findByType(haircutRequest.getType());

        if(optional.isPresent()) {
            throw new HaircutException("Service.HAIRCUT_ALREADY_EXISTS");
        }
        Haircut haircut = new Haircut();
        haircut.setDuration(haircutRequest.getDuration());
        haircut.setPrice(haircutRequest.getPrice());
        haircut.setType(haircutRequest.getType());

        haircut = haircutRepository.save(haircut);

        HaircutResponse haircutResponse = new HaircutResponse();
        haircutResponse.setHaircutId(haircut.getHaircutId());
        haircutResponse.setType(haircut.getType());
        haircutResponse.setPrice(haircut.getPrice());
        haircutResponse.setDuration(haircut.getDuration());

        return haircutResponse;

    }

}
