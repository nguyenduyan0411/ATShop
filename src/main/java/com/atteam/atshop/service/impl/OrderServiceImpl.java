package com.atteam.atshop.service.impl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atteam.atshop.dao.IOrderDAO;
import com.atteam.atshop.dao.IOrderDetailDAO;
import com.atteam.atshop.dao.IProductDAO;
import com.atteam.atshop.model.Order;
import com.atteam.atshop.model.OrderDetail;
import com.atteam.atshop.model.Product;
import com.atteam.atshop.service.IOrderService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class OrderServiceImpl implements IOrderService{

	@Autowired
	IOrderDAO dao;
	
	@Autowired
	IOrderDetailDAO orderDetailDao;
	
	@Autowired
	IProductDAO productDao;
	
	// Tạo order (đơn hàng mới của khách đặt)
	@Override
	public Order create(JsonNode orderData) {
		/*
		 * 	ObjectMapper: chuyển đổi Json thành đối tượng
		 * 
		 * */
		ObjectMapper mapper = new ObjectMapper();
		
		Order order = mapper.convertValue(orderData, Order.class);
		
		dao.save(order);
		
		/*
		 * 	TypeReference : được sử dụng để giữ thông tin về kiểu của đối tượng và giúp cho việc ánh xạ các dữ liệu
		 *  trở nên dễ dàng hơn trong quá trình chuyển đổi.
		 */		
		TypeReference<List<OrderDetail>> type = new TypeReference<List<OrderDetail>>() {};
			
		/*
		 * 	convertValue : đọc Json chuyển sang list	
		 * 
		 * 	mapper.convertValue(orderData.get("orderDetail"), type) : sau khi lấy ra
		 * 
		 * 	sẽ có được 1 list orderDetails
		 * 
		 * 	saveAll : lưu nhiều data cùng 1 lúc
		 * 
		 * */
		
		// tạo ra 1 list products
		// product.qty - orderdetail get QTY 
		List<Product> productList = productDao.findAll();
		List<OrderDetail> details = mapper.convertValue(orderData.get("orderDetails"), type)
				.stream()
				.peek(d -> d.setOrder(order))
				.collect(Collectors.toList());
		
		
		
		
		// Product : 0 1 2 3 4 5 6 7
		// Details : 0 1 2 3 4 5 6 7
//		Product product = new Product();
//		for(int i = 0; i < productList.size(); ++i) {
//			System.out.println(productList.get(i).getProductQuantity());
//			for(int j = 0; j < details.size(); ++i) {
//				if(productList.get(i).getProductId().equals(details.get(j).getProduct().getProductId())) {
//					System.out.println(details.get(j).getQuantity());
//					product = productList.get(i);
//					product.setProductQuantity(productList.get(i).getProductQuantity() - details.get(j).getQuantity());
//					System.out.println(product.getProductQuantity());
//					productDao.save(product);
//				}
//			}
//		}
		
		orderDetailDao.saveAll(details);
		
		// Cập nhật số lượng sản phẩm trong kho
		for (OrderDetail detail : details) {
			Product product = productDao.findById(detail.getProduct().getProductId()).get();
			System.out.println("So luong trong kho ban dau: " + product.getProductQuantity());
			System.out.println("So luong khach dat: " + detail.getQuantity());
			// Giảm số lượng sản phẩm trong kho
			int newQuantity = product.getProductQuantity() - detail.getQuantity();
			System.out.println("So luong sau khi cap nhat: " + newQuantity);
			product.setProductQuantity(newQuantity);
			// Cập nhật thông tin sản phẩm trong cơ sở dữ liệu
			productDao.save(product);
		}
		return order;
	}
	
	@Override
	public Order findById(Long id) {
		return dao.findById(id).get();
	}
	
	// Cho khách xem lại danh sách đơn hàng của chính khách đặt
	@Override
	public List<Order> findByUsername(String username) {
		return dao.findByUsername(username);
	}

	@Override
	public List<Order> getOrderByDay(String day) {
		return dao.getOrderByDay(day);
	}

}
