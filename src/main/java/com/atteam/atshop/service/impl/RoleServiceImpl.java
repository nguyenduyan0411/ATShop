package com.atteam.atshop.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atteam.atshop.dao.IRoleDAO;
import com.atteam.atshop.model.Role;
import com.atteam.atshop.service.IRoleService;

@Service
public class RoleServiceImpl implements IRoleService{

	@Autowired
	IRoleDAO dao;
	
	@Override
	public List<Role> findAll() {
		return dao.findAll();
	}

}
