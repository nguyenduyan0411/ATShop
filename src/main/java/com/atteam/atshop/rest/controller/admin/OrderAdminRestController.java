package com.atteam.atshop.rest.controller.admin;

import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.atteam.atshop.model.Order;
import com.atteam.atshop.service.IOrderService;
import com.atteam.atshop.service.impl.OrderServiceImpl;

@CrossOrigin("*")
@RestController
public class OrderAdminRestController {
	
	@Autowired
	IOrderService orderService = new OrderServiceImpl();
	
	// Admin: hiển thị các hóa đơn theo ngày chỉ định
	@GetMapping("/rest/order-by-day/{day}")
	public ResponseEntity<List<Order>> getQuantitiesByProduct(@PathVariable("day") String day){
		return ResponseEntity.ok(orderService.getOrderByDay(day));
	}

}
