package com.micro.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;


@Schema(name="Respone",description = "Schema to hold successfull Response!")

@Data @AllArgsConstructor
public class ResponseDto {
	
	@Schema(description = "Status code in the response")
	private String statusCode;
	
	@Schema(description = "Status message in the response")
	private String statusMsg;

}
