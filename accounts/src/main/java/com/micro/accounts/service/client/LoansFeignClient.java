package com.micro.accounts.service.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import com.micro.accounts.dto.CardsDto;
import com.micro.accounts.dto.LoansDto;


@FeignClient(name="loans",fallback = LoansFallback.class)
public interface LoansFeignClient {
	
	
    @GetMapping(value = "/api/fetch",consumes = "application/json")
	 public ResponseEntity<LoansDto> fetchLoanDetails(@RequestHeader("correlation-id") String correlationId,@RequestParam String mobileNumber) ;

}