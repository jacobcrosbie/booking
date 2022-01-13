package com.barber.booking.service;

import com.barber.booking.exceptions.HaircutException;
import com.barber.booking.service.dto.HaircutRequest;
import com.barber.booking.service.dto.HaircutResponse;

import java.util.List;

public interface HaircutService {

    HaircutResponse createHaircut(HaircutRequest haircutRequest) throws HaircutException;

    HaircutResponse getHaircutDetails(String type) throws HaircutException;

    List<HaircutResponse> getAllHaircuts();

    HaircutResponse updateHaircutDetails(HaircutRequest haircutRequest) throws HaircutException;

    String deleteHaircut(String type) throws HaircutException;
}
