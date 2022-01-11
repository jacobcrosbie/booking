package com.barber.booking.service;

import com.barber.booking.service.dto.HaircutRequest;
import com.barber.booking.service.dto.HaircutResponse;

public interface HaircutService {

    HaircutResponse createHaircut(HaircutRequest haircutRequest);
}
