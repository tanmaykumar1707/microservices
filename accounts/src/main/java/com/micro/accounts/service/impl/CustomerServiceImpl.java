package com.micro.accounts.service.impl;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import com.micro.accounts.dto.AccountsDto;
import com.micro.accounts.dto.CardsDto;
import com.micro.accounts.dto.CustomerDetailsDto;
import com.micro.accounts.dto.LoansDto;
import com.micro.accounts.entity.Accounts;
import com.micro.accounts.entity.Customer;
import com.micro.accounts.exception.ResourceNotFoundException;
import com.micro.accounts.mapper.AccountMapper;
import com.micro.accounts.mapper.CustomerMapper;
import com.micro.accounts.repository.AccountSRepository;
import com.micro.accounts.repository.CustomerRepository;
import com.micro.accounts.service.ICustomerService;
import com.micro.accounts.service.client.CardsFeignClient;
import com.micro.accounts.service.client.LoansFeignClient;

import lombok.AllArgsConstructor;


@Repository
@AllArgsConstructor
public class CustomerServiceImpl implements ICustomerService{
	
	private AccountSRepository accountsRepository;
	private CustomerRepository customerRepository;
	private CardsFeignClient cardsFeignClient;
	private LoansFeignClient loansFeignClient;
	
	@Override
	public CustomerDetailsDto fetchCustomerDetails(String mobileNumber,String correlationId) {
		
		 Customer customer = customerRepository.findByMobileNumber(mobileNumber).orElseThrow(
	                () -> new ResourceNotFoundException("Customer", "mobileNumber", mobileNumber)
	        );
	        Accounts accounts = accountsRepository.findByCustomerId(customer.getCustomerId()).orElseThrow(
	                () -> new ResourceNotFoundException("Account", "customerId", customer.getCustomerId().toString())
	        );

	        CustomerDetailsDto customerDetailsDto = CustomerMapper.mapToCustomerDetailsDto(customer, new CustomerDetailsDto());
	        customerDetailsDto.setAccountsDto(AccountMapper.mapToAccountsDto(accounts, new AccountsDto()));

	        ResponseEntity<LoansDto> loansDtoResponseEntity = loansFeignClient.fetchLoanDetails(correlationId,mobileNumber);
	       
	        if(loansDtoResponseEntity!=null)
	        customerDetailsDto.setLoansDto(loansDtoResponseEntity.getBody());
	        else {
	        	
	        }
	        
	        
	        ResponseEntity<CardsDto> cardsDtoResponseEntity = cardsFeignClient.fetchCardDetails(correlationId,mobileNumber);
	        
	        if(cardsDtoResponseEntity!=null)
	        customerDetailsDto.setCardsDto(cardsDtoResponseEntity.getBody());

	        return customerDetailsDto;
	}

}
