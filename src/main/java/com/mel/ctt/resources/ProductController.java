package com.mel.ctt.resources;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.mel.ctt.domain.Product;
import com.mel.ctt.dto.request.ProductDtoRequest;
import com.mel.ctt.dto.response.ProductDtoResponse;
import com.mel.ctt.service.ProductService;

@RestController
@RequestMapping("/products")
public class ProductController {

	@Autowired
	private ProductService productService;

	@GetMapping(path = "/all")
	public Page<Product> findAll(@RequestParam(value = "num", defaultValue = "12") Integer num) {
		Page<Product> products = productService.findAll(num);
		return products;
	}

	@GetMapping(path = "/{id}")
	public ProductDtoResponse findById(@PathVariable Long id) {
		Product product = productService.findById(id);
		ProductDtoResponse productDtoResponse = new ProductDtoResponse(product);
		return productDtoResponse;
	}

	@GetMapping("/category")
	public List<Product> findByCategory(@RequestParam(value = "idCategory") Long idCategory) {
		return this.productService.findByCategory(idCategory);
	}

	@GetMapping("/ordered")
	public List<Product> findByOrderByPrice(@RequestParam(value = "desc") Boolean desc) {
		if (desc == true) {
			return productService.findByOrderByPriceDesc();
		} else {
			return productService.findByOrderByPriceAsc();
		}
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public void create(@Valid @RequestBody ProductDtoRequest product) {
		productService.create(product);
	}
	
	@DeleteMapping(path = "/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) {
		productService.delete(id);
	}
	
	@PutMapping(path = "/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void update(@Valid @RequestBody ProductDtoRequest product, @PathVariable Long id) {
		this.productService.update(product, id);
	}

//	@GetMapping
//	public List<Product> findAll() {
//		List<Product> products = productService.findAll();
//		return products;
//	}

//	@GetMapping(path = "/{ascPrice}", produces = "application/json")
//	public List<Product> findByOrderByPriceAsc() {
//		return productService.findByOrderByPriceAsc();
//	}
//		
//	@GetMapping(path = "/{descPrice}", produces = "application/json")
//	public List<Product> findByOrderByPriceDesc() {
//		return productService.findByOrderByPriceDesc();
//	}

}
