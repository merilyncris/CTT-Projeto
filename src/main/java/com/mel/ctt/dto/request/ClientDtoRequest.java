package com.mel.ctt.dto.request;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class ClientDtoRequest {

	@Size(min = 5, max = 255, message = "Required field")
	private String email;

	@NotNull
	@Size(min = 5, max = 255, message = "Required field")
	private String address;

}
