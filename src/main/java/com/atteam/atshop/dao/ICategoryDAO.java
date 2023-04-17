package com.atteam.atshop.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.atteam.atshop.model.Category;

public interface ICategoryDAO extends JpaRepository<Category, String>{

}
