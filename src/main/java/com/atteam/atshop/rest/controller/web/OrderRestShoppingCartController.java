package com.atteam.atshop.rest.controller.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.atteam.atshop.model.Order;
import com.atteam.atshop.service.IOrderService;
import com.fasterxml.jackson.databind.JsonNode;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/order")
public class OrderRestShoppingCartController {
	@Autowired
	IOrderService orderService;
	
	@PostMapping
	public Order purchase(@RequestBody JsonNode orderData) {
		return orderService.create(orderData);
	}
}
