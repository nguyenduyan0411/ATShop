package com.atteam.atshop.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.atteam.atshop.model.Account;
import com.atteam.atshop.model.Authority;

public interface IAuthorityDAO extends JpaRepository<Authority, Integer>{

	@Query("select distinct a from Authority a where a.account in ?1")
	List<Authority> authoritiesOf(List<Account> accounts);
	
}
