package com.mel.ctt.dto.request;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class ProductDtoRequest {

	@NotNull
	@Size(min = 2)
	private String name;

	@NotNull
	private Long categoryId;

	@NotNull
	private Double price;

	@NotNull
	@Size(min = 1)
	private List<String> details = new ArrayList<>();

}
