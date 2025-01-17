package com.micro.accounts.mapper;

import com.micro.accounts.dto.AccountsDto;
import com.micro.accounts.entity.Accounts;

public class AccountMapper {
	
	public static AccountsDto mapToAccountsDto(Accounts account,AccountsDto accountsDto) {
			accountsDto.setAccountNumber(account.getAccountNumber());
			accountsDto.setAccountType(account.getAccountType());
			accountsDto.setBranchAddress(account.getBranchAddress());
			return accountsDto;
	
	}
	

	public static Accounts mapToAccounts(AccountsDto accountsDto,Accounts account) {
		account.setAccountNumber(accountsDto.getAccountNumber());
		account.setAccountType(accountsDto.getAccountType());
		account.setBranchAddress(accountsDto.getBranchAddress());
		return account;
	
	}

}
