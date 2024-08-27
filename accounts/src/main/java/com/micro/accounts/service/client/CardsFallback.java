package com.micro.accounts.service.client;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.micro.accounts.dto.CardsDto;
import com.micro.accounts.dto.LoansDto;

@Component
public class CardsFallback implements CardsFeignClient{

	@Override
	public ResponseEntity<CardsDto> fetchCardDetails(String correlationId, String mobileNumber) {
		// TODO Auto-generated method stub
		return null;
	}

	

}
