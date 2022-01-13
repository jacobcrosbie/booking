package com.barber.booking.service.impl;

import com.barber.booking.domain.Haircut;
import com.barber.booking.exceptions.HaircutException;
import com.barber.booking.repository.HaircutRepository;
import com.barber.booking.service.HaircutService;
import com.barber.booking.service.dto.HaircutRequest;
import com.barber.booking.service.dto.HaircutResponse;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class HaircutServiceImpl implements HaircutService {

    private final HaircutRepository haircutRepository;

    public HaircutResponse createHaircut(HaircutRequest haircutRequest) throws HaircutException {
        Optional<Haircut> optional = haircutRepository.findByType(haircutRequest.getType());
        if (optional.isPresent()) {
            throw new HaircutException("Service.HAIRCUT_ALREADY_EXISTS");
        }
        ModelMapper modelMapper = new ModelMapper();
        Haircut haircut = modelMapper.map(haircutRequest, Haircut.class);
        haircut = haircutRepository.save(haircut);
        ModelMapper modelMapper1 = new ModelMapper();
        return modelMapper1.map(haircut, HaircutResponse.class);

    }

    public HaircutResponse getHaircutDetails(String type) throws HaircutException {
        Optional<Haircut> optional = haircutRepository.findByType(type);
        Haircut haircut = optional.orElseThrow(() -> new HaircutException("Service.HAIRCUT_TYPE_DOES_NOT_EXIST"));
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(haircut, HaircutResponse.class);

    }

    public List<HaircutResponse> getAllHaircuts() {
        List<Haircut> haircuts = haircutRepository.findAll();
        List<HaircutResponse> haircutResponses = new ArrayList<>();
        haircuts.forEach(haircut -> {
            ModelMapper modelMapper = new ModelMapper();
            HaircutResponse haircutResponse = modelMapper.map(haircut, HaircutResponse.class);
            haircutResponses.add(haircutResponse);
        });
        return haircutResponses;
    }

    public HaircutResponse updateHaircutDetails(HaircutRequest haircutRequest) throws HaircutException {
        Optional<Haircut> optional = haircutRepository.findByType(haircutRequest.getType());
        Haircut haircut = optional.orElseThrow(() -> new HaircutException("Service.HAIRCUT_TYPE_DOES_NOT_EXIST"));
        haircut.setPrice(haircutRequest.getPrice());
        haircut.setDuration(haircutRequest.getDuration());
        haircutRepository.save(haircut);
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(haircut, HaircutResponse.class);
    }

    public String deleteHaircut(String type) throws HaircutException {
        Optional<Haircut> optional = haircutRepository.findByType(type);
        Haircut haircut = optional.orElseThrow(() -> new HaircutException("Service.HAIRCUT_TYPE_DOES_NOT_EXIST"));
        haircutRepository.deleteById(haircut.getHaircutId());
        return "Haircut '" + haircut.getType() + "' has been deleted";
    }
}
