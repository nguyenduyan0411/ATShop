package com.atteam.atshop.rest.controller.admin;

import java.util.List;
import java.util.Optional;

import javax.persistence.Id;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.atteam.atshop.model.Product;
import com.atteam.atshop.service.IProductService;
import com.atteam.atshop.service.impl.ProductServiceImpl;

@CrossOrigin("*")
@RestController
public class ProductRestController {
	@Autowired
	IProductService productService = new ProductServiceImpl();
	
	// Admin: Thống kê số lượng của từng sản phẩm theo danh mục
	@GetMapping("/rest/product-quantity/{id}")
	public ResponseEntity<List<Product>> getQuantitiesByProduct(@PathVariable("id") String id){
		return ResponseEntity.ok(productService.findByCategoryId(id));
	}

	
	// Get all products
	@GetMapping("/rest/product")
	public ResponseEntity<List<Product>> getAll(){
		return ResponseEntity.ok(productService.findAll());
	}
	
	// Get one product by id
	@GetMapping("/rest/product/{id}")
	public ResponseEntity<Product> findOne(@PathVariable("id") String id){
		if(productService.existsById(id)) {
			return ResponseEntity.ok(productService.findById(id));
		}
		return ResponseEntity.notFound().build();
	}
	
	// Create a new product
	@PostMapping("/rest/product")
	public ResponseEntity<Product> post(@RequestBody Product product){
		if(productService.existsById(product.getProductId())) {
			return ResponseEntity.badRequest().build(); // 400 bad request
		}
		productService.create(product);
		return ResponseEntity.ok(product);
	}
	
	// Update a product
	@PutMapping("/rest/product/{id}")
	public ResponseEntity<Product> put(@PathVariable("id") String id, @RequestBody Product product){
		if(productService.existsById(id)) {
			productService.update(product);
			return ResponseEntity.ok(product);
		}
		return ResponseEntity.notFound().build(); // 404 not found
	}
	
	// Delete category
	@DeleteMapping("/rest/product/{productId}")
	public ResponseEntity<Void> delete(@PathVariable("productId") String productId){
		if(productService.existsById(productId)) {
			productService.deleteById(productId);
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build(); // 404 not found
	}
	
}
