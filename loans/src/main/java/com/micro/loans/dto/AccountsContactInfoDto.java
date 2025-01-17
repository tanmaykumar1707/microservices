package com.micro.loans.dto;

import java.util.List;
import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
@ConfigurationProperties("loans")
public class AccountsContactInfoDto {
	
	String message;
	Map<String,String> contactDetails;
	List<String> onCallSupport;

}