package com.mel.ctt.dto.response;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mel.ctt.domain.Category;
import com.mel.ctt.domain.Product;

import lombok.Data;

@Data
public class ProductDtoResponse {

	private Long id;

	private String name;

	private Category category;

	private Double price;

	@JsonIgnore(false)
	private List<String> details = new ArrayList<>();

	public ProductDtoResponse(Product product) {
		this.setId(product.getId());
		this.setName(product.getName());
		this.setCategory(product.getCategory());
		this.setPrice(product.getPrice());
		this.setDetails(product.getDetails());
	}
}
