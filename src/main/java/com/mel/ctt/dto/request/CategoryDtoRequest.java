package com.mel.ctt.dto.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class CategoryDtoRequest {

	@NotBlank
	private String name;

	@NotBlank
	@Size(max = 3)
	private String code;

}
