package com.atteam.atshop.rest.controller.admin;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.atteam.atshop.model.Account;
import com.atteam.atshop.service.IAccountService;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/accounts")
public class AccountRestController {
	@Autowired
	IAccountService accountService;
	
	@GetMapping
	public List<Account> getAccounts(@RequestParam("admin") Optional<Boolean> admin){
		if(admin.orElse(false)) {
			return accountService.getAdministrators();
		}
		return accountService.findAll();
	}
}
