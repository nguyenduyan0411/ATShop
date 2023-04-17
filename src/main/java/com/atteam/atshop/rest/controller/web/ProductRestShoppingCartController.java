package com.atteam.atshop.rest.controller.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.atteam.atshop.model.Product;
import com.atteam.atshop.service.IProductService;

@CrossOrigin("*")
@RestController
@RequestMapping("/web/rest/product")
public class ProductRestShoppingCartController {
	@Autowired
	IProductService productService;
	
	@GetMapping("{id}")
	public Product getOne(@PathVariable("id") String id) {
		return productService.findById(id);
	}
}
