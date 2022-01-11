package com.barber.booking.service.impl;

import com.barber.booking.domain.Haircut;
import com.barber.booking.repository.HaircutRepository;
import com.barber.booking.service.HaircutService;
import com.barber.booking.service.dto.HaircutRequest;
import com.barber.booking.service.dto.HaircutResponse;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class HaircutServiceImpl implements HaircutService {

    private final HaircutRepository haircutRepository;

    public HaircutResponse createHaircut(HaircutRequest haircutRequest) {

        ModelMapper modelMapper = new ModelMapper();
        Haircut haircut = modelMapper.map(haircutRequest,Haircut.class);
        haircut = haircutRepository.save(haircut);

        ModelMapper modelMapper1 = new ModelMapper();
        HaircutResponse haircutResponse = modelMapper1.map(haircut,HaircutResponse.class);

        return haircutResponse;

    }

}
