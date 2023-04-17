package com.atteam.atshop.service;

import java.util.List;

import com.atteam.atshop.model.Account;

public interface IAccountService {
	
	Account findById(String username);
	
	Account create(Account account);
	
	List<Account> findAll();

	List<Account> getAdministrators();

	Account resetPassword(String email);
	
}
