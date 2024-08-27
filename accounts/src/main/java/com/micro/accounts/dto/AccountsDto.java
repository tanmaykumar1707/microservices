package com.micro.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
@Schema(name="Account",description = "Schema to hold Account informaton!")
public class AccountsDto {
	
	@Schema(description = "accountNumber")
	@NotEmpty(message="Account number can not be null or empty")
	@Pattern(regexp="(^$[0-9]{10})",message="Account number must be 10 digit!")
	private Long accountNumber;
	
	@Schema(description = "Account Type of the customer")
	@NotEmpty(message="AccountType can not be a null or empty")
	private String accountType;
	
	@Schema(description = "Branch Address")
	@NotEmpty(message="Branch Address can not be null or empty")
	private String branchAddress;

}
