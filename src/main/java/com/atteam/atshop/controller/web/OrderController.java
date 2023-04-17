 package com.atteam.atshop.controller.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.atteam.atshop.service.IOrderService;

@Controller
public class OrderController {
	
	@Autowired
	IOrderService orderService;
	
	@RequestMapping("/order/checkout")
	public String checkout() {
		return "web/order/checkout";
	}
	
	// Cho khách xem lại danh sách đơn hàng của chính khách đặt
	@RequestMapping("/order/list")
	public String list(Model model, HttpServletRequest request) {
		String username = request.getRemoteUser(); // username của người đăng nhập
		model.addAttribute("orders", orderService.findByUsername(username));
		return "web/order/list";
	}
	
	@RequestMapping("/order/detail/{orderId}")
	public String detail(Model model, @PathVariable("orderId") Long orderId) {
		model.addAttribute("order", orderService.findById(orderId));
		return "web/order/detail";
	}
	
}
