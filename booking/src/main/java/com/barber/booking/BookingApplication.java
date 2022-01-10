package com.barber.booking;

import com.stripe.Stripe;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class BookingApplication {

	@Value("${stripe.api.key}")
	private String stripeApiKey;

	@PostConstruct
	public void setup(){
		Stripe.apiKey = stripeApiKey; // only have to set this once
	}

	public static void main(String[] args) {
		SpringApplication.run(BookingApplication.class, args);
	}

}
