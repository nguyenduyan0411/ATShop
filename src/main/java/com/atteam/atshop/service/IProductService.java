package com.atteam.atshop.service;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import com.atteam.atshop.model.Product;

public interface IProductService {
	
	List<Product> findAll();

	// load products by categoryid
	List<Product> findByCategoryId(String cid);
	
	// Lọc sản phẩm theo ngày đăng mới nhất
	List<Product> sortByProductLatest();
	
	// Lọc sản phẩm theo ngày cũ nhất
	List<Product> sortByProductOldestDay();

	// load product detail page
	Product findById(String id);

	boolean existsById(String id);

	Product create(Product product);
	Product update(Product product);
	public void deleteById(String id);
	
	// Lọc 8 sản phẩm theo ngày đăng mới nhất. Hiển thị trên trang HOME
	List<Product> findRecentProducts();
	
	// Search product name or product id
	List<Product> searchByProductNameOrId(String productName, String productId);
	
	List<Product> findAllProductCustomerLike(String username);
	
}
