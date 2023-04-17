package com.atteam.atshop.controller.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ShoppingCartController {
	// xem giỏ hàng
	@RequestMapping("/cart/view")
	public String view() {
		return "web/cart/view";
	}
}
