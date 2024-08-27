package com.micro.accounts.dto;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Schema(name="Respone",description = "Schema to hold error Response!")

@Data @NoArgsConstructor @AllArgsConstructor
public class ErrorResponseDto {

	@Schema(description = "Api Path invoked by the client")
	private String apiPath;
	
	
	@Schema(
            description = "Error code representing the error happened"
    )
	private HttpStatus errorCode;
	
	@Schema(
            description = "Error message representing the error happened"
    )
	private String errorMessage;
	
	
	@Schema(
            description = "Time representing when the error happened"
    )
    
	private LocalDateTime errorTime;
	
}
