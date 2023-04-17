package com.atteam.atshop.rest.controller.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.atteam.atshop.model.Category;
import com.atteam.atshop.service.ICategoryService;
import com.atteam.atshop.service.impl.CategoryServiceImpl;

@CrossOrigin("*")
@RestController
public class CategoryRestController {
	@Autowired
	ICategoryService categoryService = new CategoryServiceImpl();
	
	// Get all categories
	@GetMapping("/rest/category")
	public ResponseEntity<List<Category>> getAll(){
		return ResponseEntity.ok(categoryService.findAll());
	}
	
	// Get one category by id
	@GetMapping("/rest/category/{categoryId}")
	public ResponseEntity<Category> findOne(@PathVariable("categoryId") String categoryId){
		if(categoryService.existsById(categoryId)) {
			return ResponseEntity.ok(categoryService.findById(categoryId));
		}
		return ResponseEntity.notFound().build();
	}
	
	// Create a new category
	@PostMapping("/rest/category")
	public ResponseEntity<Category> post(@RequestBody Category category){
		if(categoryService.existsById(category.getCategoryId())) {
			return ResponseEntity.badRequest().build(); // 400 bad request
		}
		categoryService.create(category);
		return ResponseEntity.ok(category);
	}
	
	// Update a category
	@PutMapping("/rest/category/{categoryId}")
	public ResponseEntity<Category> put(@PathVariable("categoryId") String categoryId, @RequestBody Category category){
		if(categoryService.existsById(categoryId)) {
			categoryService.update(category);
			return ResponseEntity.ok(category);
		}
		return ResponseEntity.notFound().build(); // 404 not found
	}
	
	// Delete category
	@DeleteMapping("/rest/category/{categoryId}")
	public ResponseEntity<Void> delete(@PathVariable("categoryId") String categoryId){
		if(categoryService.existsById(categoryId)) {
			categoryService.deleteById(categoryId);
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build(); // 404 not found
	}
}
