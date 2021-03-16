package com.mel.ctt.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mel.ctt.dto.request.CategoryDtoRequest;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@EqualsAndHashCode(of = "id")
public class Category implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	@Size(min = 2, message = "Required Field")
	private String name;

	@Column(nullable = false)
	@Size(min = 3)
	private String code;

	@JsonIgnore
	@OneToMany(mappedBy = "category")
	private List<Product> products = new ArrayList<>();

	public Category() {

	}

	public Category(CategoryDtoRequest categoryDtoRequest) {
		this.setName(categoryDtoRequest.getName());
		this.setCode(categoryDtoRequest.getCode());
	}

}
