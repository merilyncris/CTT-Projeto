package com.mel.ctt.service;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.mel.ctt.domain.Category;
import com.mel.ctt.domain.Product;
import com.mel.ctt.dto.request.ProductDtoRequest;
import com.mel.ctt.exception.InvalidCategory;
import com.mel.ctt.exception.NotFound;
import com.mel.ctt.repository.CategoryRepository;
import com.mel.ctt.repository.ProductRepository;

@Service
public class ProductService implements Serializable {

	private static final long serialVersionUID = 1L;

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private CategoryRepository categoryRepository;

//	public List<Product> findAll(){
//		List<Product> products = productRepository.findAll();
//		return products;
//	}

	public Page<Product> findAll(Integer num) {
		Page<Product> products = this.productRepository
				.findAll(PageRequest.of(0, num, Sort.by(Sort.Direction.ASC, "name")));
		return products;
	}

	public Product findById(Long id) {
		Product product = this.productRepository.findById(id).orElseThrow(() -> new NotFound());
		return product;
	}

	public void create(ProductDtoRequest productDtoRequest) {
		Category category = categoryRepository.findById(productDtoRequest.getCategoryId())
				.orElseThrow(() -> new InvalidCategory());

		Product productSave = new Product(productDtoRequest, category);
		productSave.setName(productDtoRequest.getName());
		productSave.setPrice(productDtoRequest.getPrice());
		productSave.setDetails(productDtoRequest.getDetails());
		productSave.setCategory(category);
		productRepository.save(productSave);
	}

	public Product update(ProductDtoRequest productDtoRequest, Long id) {
		Product product = this.findById(id);
		product.fillFromDto(productDtoRequest);

		Category category = categoryRepository.findById(productDtoRequest.getCategoryId())
				.orElseThrow(() -> new InvalidCategory());
		product.setCategory(category);

		return productRepository.save(product);
	}

	public List<Product> findByOrderByPriceAsc() {
		return productRepository.findByOrderByPriceAsc();
	}

	public List<Product> findByOrderByPriceDesc() {
		return productRepository.findByOrderByPriceDesc();
	}

	public List<Product> findByCategory(Long categoryId) {
		Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new NotFound());
		return productRepository.findByCategory(category);
	}

	public void delete(Long id) {
		Product product = productRepository.findById(id).orElseThrow(() -> new NotFound());
		productRepository.delete(product);
	}

}
