package com.micro.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Schema(name="Customer",description = "Schema to hold Customer and Account informaton!")
public class CustomerDto {
	
	@Schema(description = "name of the customer",example = "Kumar")
	@NotEmpty(message ="Name can not be blankS")
	@Size(min=5,max=30,message="Customer name should be 5 and  30")
	private String name;
	
	@Schema(description = "email of the customer",example = "kumar@test.com")
	@NotEmpty(message="email can not be blank!")
	@Email(message="Email value must be validS")
	private String email;
	
	@Schema(description = "Mobile number of the customer",example = "9999999999")
    @Pattern(regexp="(^$|[0-9]{10})",message = "Mobile Number must be 10 digits")
	private String mobileNumber;
	
	private AccountsDto accountsDto;


}
