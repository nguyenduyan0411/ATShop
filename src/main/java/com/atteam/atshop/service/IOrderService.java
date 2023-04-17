package com.atteam.atshop.service;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;

import com.atteam.atshop.model.Order;
import com.atteam.atshop.model.OrderDetail;
import com.fasterxml.jackson.databind.JsonNode;

public interface IOrderService {

	Order create(JsonNode orderData);
	
	// Khi đặt hàng thành công rồi. Sẽ hiển thị lại hóa đơn cho khách hàng
	Order findById(Long id);
	
	// Cho khách xem lại danh sách đơn hàng của chính khách đặt
	List<Order> findByUsername(String username);
	
	// Admin: hiển thị các hóa đơn theo ngày chỉ định
	List<Order> getOrderByDay(String day);

}
