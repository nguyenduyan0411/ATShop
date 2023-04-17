package com.atteam.atshop.rest.controller.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.atteam.atshop.model.Account;
import com.atteam.atshop.model.Favourite;

import com.atteam.atshop.service.IAccountService;
import com.atteam.atshop.service.IFavouriteService;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/favourite")
public class FavouriteRestController {
	@Autowired
	IFavouriteService favouriteService;
	
	@Autowired
	IAccountService accountService;
	
	@GetMapping("/{id}")
	public Favourite check(HttpServletRequest request, @PathVariable("id") String id) {
		//Kiểm tra Favourite có tồn tại hay không
		String username = request.getRemoteUser();
		Favourite favourite = favouriteService.findByUsernameAndProductId(username, id);
		return favourite;
	}
	
	@PutMapping("/{id}")
	public Favourite update(HttpServletRequest request, @PathVariable("id") String id) {
		Account account = accountService.findById(request.getRemoteUser());
		Favourite favourite = favouriteService.updateLikeOrUnlike(account, id);
		return favourite;
	}

}
