package com.atteam.atshop.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atteam.atshop.dao.IAccountDAO;
import com.atteam.atshop.dao.IAuthorityDAO;
import com.atteam.atshop.model.Account;
import com.atteam.atshop.model.Authority;
import com.atteam.atshop.service.IAuthorityService;

@Service
public class AuthorityServiceImpl implements IAuthorityService{

	@Autowired
	IAuthorityDAO authDao;
	
	@Autowired
	IAccountDAO accDao;
	
	@Override
	public List<Authority> findAll() {
		return authDao.findAll();
	}

	@Override
	public Authority create(Authority auth) {
		return authDao.save(auth);
	}

	@Override
	public void delete(Integer id) {
		authDao.deleteById(id);
	}

	@Override
	public List<Authority> findAuthoritiesOfAdministrators() {
		List<Account> accounts = accDao.getAdministrators();
		return authDao.authoritiesOf(accounts);
	}

}
