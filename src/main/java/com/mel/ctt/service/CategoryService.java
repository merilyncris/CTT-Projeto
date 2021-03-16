package com.mel.ctt.service;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mel.ctt.domain.Category;
import com.mel.ctt.dto.request.CategoryDtoRequest;
import com.mel.ctt.exception.NotFound;
import com.mel.ctt.repository.CategoryRepository;

@Service
public class CategoryService implements Serializable {

	private static final long serialVersionUID = 1L;

	@Autowired
	private CategoryRepository categoryRepository;

	public List<Category> findAll() {
		List<Category> categories = categoryRepository.findAll();
		return categories;
	}

	public void save(CategoryDtoRequest categoryDtoRequest) {
		Category categorySave = new Category(categoryDtoRequest);
		categorySave.setName(categoryDtoRequest.getName());
		categorySave.setCode(categoryDtoRequest.getCode());

		this.categoryRepository.save(categorySave);

		// setting code
		Long gettingId = categorySave.getId();
		String processingCode = Long.toString(gettingId);
		String outsiderCode = categorySave.getCode();
		String finalCode = outsiderCode + "0" + processingCode;
		categorySave.setCode(finalCode);
		// setting code

		this.categoryRepository.save(categorySave);
	}

	public Category findById(Long id) {
		Category category = categoryRepository.findById(id).orElseThrow(() -> new NotFound());
		return category;
	}

}
