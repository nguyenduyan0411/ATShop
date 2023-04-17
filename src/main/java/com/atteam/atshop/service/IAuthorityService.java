package com.atteam.atshop.service;

import java.util.List;

import com.atteam.atshop.model.Authority;

public interface IAuthorityService {
	
	List<Authority> findAll();
	
	Authority create(Authority auth);
	
	void delete(Integer id);
	
	List<Authority> findAuthoritiesOfAdministrators();
}
