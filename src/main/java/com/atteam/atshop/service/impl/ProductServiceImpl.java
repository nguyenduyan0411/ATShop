package com.atteam.atshop.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atteam.atshop.dao.IProductDAO;
import com.atteam.atshop.model.Product;
import com.atteam.atshop.service.IProductService;

@Service
public class ProductServiceImpl implements IProductService{
	
	@Autowired
	IProductDAO dao;

	@Override
	public List<Product> findAll() {
		return dao.findAll();
	}

	@Override
	public List<Product> findByCategoryId(String cid) {
		return dao.findByCategoryId(cid);
	}

	@Override
	public Product findById(String id) {
		return dao.findById(id).get();
	}

	@Override
	public boolean existsById(String productId) {
		if(dao.existsById(productId))
			return true;
		return false;
	}

	@Override
	public Product create(Product product) {
		return dao.save(product);
	}

	@Override
	public Product update(Product product) {
		return dao.save(product);
	}

	@Override
	public void deleteById(String id) {
		dao.deleteById(id);	
	}

	@Override
	public List<Product> sortByProductLatest() {
		return dao.sortByProductLatest();
	}

	@Override
	public List<Product> sortByProductOldestDay() {
		return dao.sortByProductOldestDay();
	}

	// Lọc 8 sản phẩm theo ngày đăng mới nhất. Hiển thị trên trang HOME
	@Override
	public List<Product> findRecentProducts() {
		return dao.findRecentProducts();
	}

	@Override
	public List<Product> searchByProductNameOrId(String productName, String productId) {
		return dao.searchByProductNameOrId(productName, productId);
	}

	@Override
	public List<Product> findAllProductCustomerLike(String username) {
		return dao.findAllProductCustomerLike(username);
	}

}
