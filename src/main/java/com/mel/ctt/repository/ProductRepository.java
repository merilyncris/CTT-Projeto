package com.mel.ctt.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mel.ctt.domain.Category;
import com.mel.ctt.domain.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

	List<Product> findProductByIdIn(List<Long> productId);

	List<Product> findByOrderByPriceAsc();

	List<Product> findByOrderByPriceDesc();

	List<Product> findByCategory(Category category);
}
