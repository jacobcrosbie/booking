package com.barber.booking.service.impl;

import com.barber.booking.domain.Haircut;
import com.barber.booking.repository.HaircutRepository;
import com.barber.booking.service.HaircutService;
import com.barber.booking.service.dto.HaircutRequest;
import com.barber.booking.service.dto.HaircutResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class HaircutServiceImpl implements HaircutService {

    private final HaircutRepository haircutRepository;

    public HaircutResponse createHaircut(HaircutRequest haircutRequest) {

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
