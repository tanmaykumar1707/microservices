package com.micro.accounts.service.impl;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

import org.springframework.stereotype.Service;

import com.micro.accounts.constants.AccountsConstants;
import com.micro.accounts.dto.AccountsDto;
import com.micro.accounts.dto.CustomerDto;
import com.micro.accounts.entity.Accounts;
import com.micro.accounts.entity.Customer;
import com.micro.accounts.exception.CustomerAlreadyExistsException;
import com.micro.accounts.exception.ResourceNotFoundException;
import com.micro.accounts.mapper.AccountMapper;
import com.micro.accounts.mapper.CustomerMapper;
import com.micro.accounts.repository.AccountSRepository;
import com.micro.accounts.repository.CustomerRepository;
import com.micro.accounts.service.IAccountsService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor // if constructor no need of AUtowired keyword
public class AccountServiceImpl implements IAccountsService{
	
	private AccountSRepository accountSRepository;
	private CustomerRepository customerRepository;

	@Override
	public void createAccount(CustomerDto customerDto) {
		Customer customer = CustomerMapper.mapToCustomer(customerDto, new Customer());
		Optional<Customer> optionalCustomer = customerRepository.findByMobileNumber(customer.getMobileNumber());
		optionalCustomer.ifPresent(  
				item ->  {
					throw new CustomerAlreadyExistsException("Customer already present with mobile number "+item.getMobileNumber());
				}
				);
//		customer.setCreatedAt(LocalDateTime.now());
//		customer.setCreatedBy("Ananymous");
		Customer saveCustomer = customerRepository.save(customer);
		accountSRepository.save(createNewAccount(saveCustomer));
		
	}
	
	private Accounts createNewAccount(Customer customer) {
		Accounts newAccount = new Accounts();
		newAccount.setCustomerId(customer.getCustomerId());
		
        long randomAccNumber = 1000000000L + new Random().nextInt(900000000);
		newAccount.setAccountNumber(randomAccNumber);		
		newAccount.setAccountType(AccountsConstants.SAVINGS);
		newAccount.setBranchAddress(AccountsConstants.ADDRESS);
//		newAccount.setCreatedAt(LocalDateTime.now());
//		newAccount.setCreatedBy("Ananymous");
		return newAccount;
	}

	@Override
	public CustomerDto fetchAccount(String mobileNumber) {
		
		
		
			Customer customer =  customerRepository.findByMobileNumber(mobileNumber)
					.orElseThrow(  () -> new ResourceNotFoundException("Customer", "MobileNumber", mobileNumber) ) ;
			
			Accounts account = accountSRepository.findByCustomerId(customer.getCustomerId()).orElseThrow(
					()->  new ResourceNotFoundException("Account", "CustomerId", customer.getCustomerId().toString())
					);
			
			AccountsDto accountsDto = AccountMapper.mapToAccountsDto(account, new AccountsDto());
			CustomerDto customerDto = CustomerMapper.mapToCustomerDto(customer, new CustomerDto());
			customerDto.setAccountsDto(accountsDto);
			
		return customerDto;
	}

	@Override
	public boolean updateAccount(CustomerDto customerDto) {
		boolean isUpdated = false;
		AccountsDto accountDto = customerDto.getAccountsDto();
		if(accountDto!=null) {
			Accounts accounts= accountSRepository.findById(accountDto.getAccountNumber()).orElseThrow(
					()-> new ResourceNotFoundException("Account", "AccountNumber", accountDto.getAccountNumber().toString()));
		
			AccountMapper.mapToAccounts(accountDto, accounts);
			accounts = accountSRepository.save(accounts);
			
			Long customerId = accounts.getCustomerId();
			Customer customer = customerRepository.findById(customerId).orElseThrow(
					()-> new ResourceNotFoundException("Customer", "CustomerId", customerId.toString())
					);
			CustomerMapper.mapToCustomer(customerDto, customer);
			customerRepository.save(customer);
			isUpdated=true;
		
		}
		return isUpdated;
	}

	@Override
	public boolean deleteAccount(String mobileNumber) {

		Customer customer =  customerRepository.findByMobileNumber(mobileNumber)
				.orElseThrow(  () -> new ResourceNotFoundException("Customer", "MobileNumber", mobileNumber) ) ;
		
		accountSRepository.deleteByCustomerId(customer.getCustomerId());
		customerRepository.deleteById(customer.getCustomerId());
		
		
		return true;
	}
	
	
	

}
