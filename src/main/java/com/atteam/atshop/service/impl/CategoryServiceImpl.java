package com.atteam.atshop.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atteam.atshop.dao.ICategoryDAO;
import com.atteam.atshop.model.Category;
import com.atteam.atshop.service.ICategoryService;

@Service
public class CategoryServiceImpl implements ICategoryService{
	@Autowired
	ICategoryDAO dao;

	@Override
	public List<Category> findAll() {
		return dao.findAll();
	}

	
	@Override
	public boolean existsById(String id) {
		if(dao.existsById(id))
			return true;
		return false;
	}


	@Override
	public Category findById(String id) {
		return dao.findById(id).get();
	}


	@Override
	public Category create(Category category) {
		return dao.save(category);
	}


	@Override
	public Category update(Category category) {
		return dao.save(category);
	}


	@Override
	public void deleteById(String categoryId) {
		dao.deleteById(categoryId);
	}
}
