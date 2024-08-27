package com.micro.gatewayserver.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Mono;

@RestController
public class FallbackController {
	
	
	
	@RequestMapping("/contactSupport")
	public Mono<String> contactSupport(){
		
		return Mono.just("Service is not availaible for now , Please wait ..");
	}
	

}
