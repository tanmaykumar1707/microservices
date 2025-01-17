package com.micro.accounts.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.micro.accounts.constants.AccountsConstants;
import com.micro.accounts.dto.AccountsContactInfoDto;
import com.micro.accounts.dto.CustomerDto;
import com.micro.accounts.dto.ErrorResponseDto;
import com.micro.accounts.dto.ResponseDto;
import com.micro.accounts.service.IAccountsService;

import ch.qos.logback.classic.Logger;
import io.github.resilience4j.retry.annotation.Retry;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PutMapping;



//common info for Rest Controllers
@Tag( name="Crud Rest Apis for account in Accounts Microservice",
		description="Crud Rest Apis for Create, Update, Fetch and Delete"
	)

@RestController
@RequestMapping(path="/api",produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
@Validated
public class AccountsControllers {
	
	private IAccountsService iAccountsService;
	
	private AccountsContactInfoDto accountInfoDto;
	
	@Operation(summary="Create Account Api",
			description="Pass the data to create the account"
			)
	
	@ApiResponses({
		
		@ApiResponse (
				responseCode = "201",
                description = "HTTP Status CREATED"
				),
		@ApiResponse (
				 responseCode = "500",
                 description = "HTTP Status Internal Server Error"
				)
	})
		
	@PostMapping("/create")
	public ResponseEntity<ResponseDto> createAccount (@Valid @RequestBody CustomerDto customerDto){
		
		iAccountsService.createAccount(customerDto);
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(new ResponseDto(AccountsConstants.STATUS_201	, AccountsConstants.MESSAGE_201));
	}
	
	
	@Operation(summary = "Fetch Acount Api", description ="Fetch Account")
	 @ApiResponses({
         @ApiResponse(
                 responseCode = "200",
                 description = "HTTP Status OK"
         )
//         ,
//         @ApiResponse(
//                 responseCode = "500",
//                 description = "HTTP Status Internal Server Error",
//                 content = @Content(schema = @Schema(implementation = ErrorResponseDto.class))
//
//         )
 }
 )
	@GetMapping("/fetch")
	public ResponseEntity<CustomerDto> fetchAccountDetails(@RequestParam 
    		@Pattern(regexp="(^$|[0-9]{10})",message = "Mobile Number must be 10 digits")

			String mobileNumber){
		
		CustomerDto customerDto = iAccountsService.fetchAccount(mobileNumber);
		
		return ResponseEntity.status(HttpStatus.OK).body(customerDto);
	}
	

	@Operation(summary = "Udate Acount Api", description ="Update Account")
	 @ApiResponses({
         @ApiResponse(
                 responseCode = "200",
                 description = "HTTP Status OK",
                 content = @Content(schema = @Schema(implementation = ResponseDto.class)) // Assuming no content is returned for 200

         ),
         @ApiResponse(
                 responseCode = "417",
                 description = "Expectation Failed",
                 content = @Content(schema = @Schema(implementation = Void.class)) // Assuming no content is returned for 200

         )
         ,
         @ApiResponse(
                 responseCode = "500",
                 description = "HTTP Status Internal Server Error",
                 content = @Content(
                		 schema = @Schema(implementation = ErrorResponseDto.class)
                		 )

         )
 }
 )
	@PutMapping("/update")
	public ResponseEntity<ResponseDto> updateAccountDetails(@Valid @RequestBody CustomerDto cutomerDto) {
		
		boolean  isUpdated = iAccountsService.updateAccount(cutomerDto);
		if(isUpdated) {
			return ResponseEntity.status(HttpStatus.OK).body(
					new ResponseDto(AccountsConstants.STATUS_200, AccountsConstants.MESSAGE_200));
		}else {
			
//			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
//					new ResponseDto(AccountsConstants.STATUS_500, AccountsConstants.MESSAGE_500)); 
			
			 return ResponseEntity
	                    .status(HttpStatus.EXPECTATION_FAILED)
	                    .body(new ResponseDto(AccountsConstants.STATUS_417, AccountsConstants.MESSAGE_417_UPDATE));
			
			
		}

	}

	@Operation(summary = "Delete Acount Api", description ="Delete Account")
	 @ApiResponses({
         @ApiResponse(
                 responseCode = "200",
                 description = "HTTP Status OK"
         ),
         @ApiResponse(
                 responseCode = "417",
                 description = "Expectation Failed"
         )
//         ,
//         @ApiResponse(
//                 responseCode = "500",
//                 description = "HTTP Status Internal Server Error",
//                 content = @Content(schema = @Schema(implementation = ErrorResponseDto.class))
//         )
 }
 )
	
	@DeleteMapping("/delete")
	public ResponseEntity<ResponseDto> deleteAccounts(@RequestParam 
    		@Pattern(regexp="(^$|[0-9]{10})",message = "Mobile Number must be 10 digits")

			String mobileNumber)
	{
		
		boolean isDeleted = iAccountsService.deleteAccount(mobileNumber);
        if(isDeleted) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDto(AccountsConstants.STATUS_200, AccountsConstants.MESSAGE_200));
        }else{
//            return ResponseEntity
//                    .status(HttpStatus.EXPECTATION_FAILED)
//                    .body(new ResponseDto(AccountsConstants.STATUS_417, AccountsConstants.MESSAGE_417_DELETE));
     
        	 return ResponseEntity
                     .status(HttpStatus.EXPECTATION_FAILED)
                     .body(new ResponseDto(AccountsConstants.STATUS_417, AccountsConstants.MESSAGE_417_DELETE));
        }
	
	}
	
	@Retry(name = "getInfo",fallbackMethod = "getInfoFallBack")
	@GetMapping("/info")
	public ResponseEntity<AccountsContactInfoDto> getInfoDetails(){
		throw new RuntimeException();
		//return ResponseEntity.status(HttpStatus.OK).body(accountInfoDto);
	}
	
	public ResponseEntity<AccountsContactInfoDto> getInfoFallBack(Throwable throwable){
		System.out.println("fallback=====");
		accountInfoDto.setMessage("This came from fallback endpoint");
		return ResponseEntity.status(HttpStatus.OK).body(accountInfoDto);
	}
	
	
	
}
