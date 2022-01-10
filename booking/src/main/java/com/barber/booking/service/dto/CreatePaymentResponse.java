package com.barber.booking.service.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreatePaymentResponse {
    private String clientSecret;

    public CreatePaymentResponse(String clientSecret) {
        this.clientSecret = clientSecret;
    }
}
