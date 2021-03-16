package com.mel.ctt.dto.response;

import java.util.ArrayList;
import java.util.List;

import com.mel.ctt.domain.Category;
import com.mel.ctt.domain.Product;

import lombok.Data;

@Data
public class CategoryDtoResponse {

	private String name;

	private String code;

	private List<Product> products = new ArrayList<>();

	public CategoryDtoResponse(Category category) {
		this.name = category.getName();
		this.code = category.getCode();
		this.products = category.getProducts();

	}
}
