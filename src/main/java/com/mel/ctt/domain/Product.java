package com.mel.ctt.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.mel.ctt.dto.request.ProductDtoRequest;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(of = "id")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class Product implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String name;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_category", referencedColumnName = "id")
	private Category category;

	@Column(nullable = false)
	private Double price;

	@Column(nullable = false)
	@ElementCollection
	@JsonIgnore
	private List<String> details = new ArrayList<>();

	public Product(ProductDtoRequest productDtoRequest, Category category) {
		this.setCategory(category);
		this.fillFromDto(productDtoRequest);

	}

	public void fillFromDto(ProductDtoRequest productDtoRequest) {
		this.setName(productDtoRequest.getName());
		this.setPrice(productDtoRequest.getPrice());
		this.setDetails(productDtoRequest.getDetails());
	}

	public Product() {

	}

}
