package com.atteam.atshop.service;

import java.util.List;

import com.atteam.atshop.model.Category;

public interface ICategoryService {

	List<Category> findAll();

	boolean existsById(String id);

	Category findById(String id);

	// Add new category
	Category create(Category category);

	// Update category
	Category update(Category category);
	
	// Delete category
	public void deleteById(String categoryId);

}
