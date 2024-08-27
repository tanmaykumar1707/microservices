package com.micro.accounts.mapper;

import com.micro.accounts.dto.CustomerDetailsDto;
import com.micro.accounts.dto.CustomerDto;
import com.micro.accounts.entity.Customer;

public class CustomerMapper {
	
	
	public static CustomerDto mapToCustomerDto(Customer customer,CustomerDto customerDto) {
		customerDto.setEmail(customer.getEmail());
		customerDto.setMobileNumber(customer.getMobileNumber());
		customerDto.setName(customer.getName());

		return customerDto;
		
	}
	
	public static Customer mapToCustomer(CustomerDto customerDto,Customer customer) {
		customer.setEmail(customerDto.getEmail());
		customer.setMobileNumber(customerDto.getMobileNumber());
		customer.setName(customerDto.getName());

		return customer;
		
	}

	public static CustomerDetailsDto mapToCustomerDetailsDto(Customer customer, CustomerDetailsDto customerDetailsDto) {
		customerDetailsDto.setEmail(customer.getEmail());
		customerDetailsDto.setMobileNumber(customer.getMobileNumber());
		customerDetailsDto.setName(customer.getName());

		return customerDetailsDto;
	}

}
