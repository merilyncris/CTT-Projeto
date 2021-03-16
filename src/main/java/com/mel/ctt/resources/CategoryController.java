package com.mel.ctt.resources;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.mel.ctt.domain.Category;
import com.mel.ctt.dto.request.CategoryDtoRequest;
import com.mel.ctt.service.CategoryService;

@RestController
@RequestMapping("/categories")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;

	@GetMapping
	public List<Category> findAll() {
		List<Category> categories = categoryService.findAll();
		return categories;
	}

	@GetMapping(path = "/{id}", produces = "application/json")
	public Category findById(@PathVariable Long id) {
		return categoryService.findById(id);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public void create(@Valid @RequestBody CategoryDtoRequest categoryDtoCategory) {
		categoryService.save(categoryDtoCategory);
	}

}
