package com.atteam.atshop.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.atteam.atshop.model.Account;

public interface IAccountDAO extends JpaRepository<Account, String>{
	
	// Lấy tất cả account có vai trò DIRE và Staf
	@Query("select distinct ar.account from Authority ar where ar.role.id in ('DIRE', 'STAF')")
	List<Account> getAdministrators();

	
	@Query(value = "select * from Accounts where UserEmail = ?1", nativeQuery = true)
	Account findByEmail(String email);
	
}
