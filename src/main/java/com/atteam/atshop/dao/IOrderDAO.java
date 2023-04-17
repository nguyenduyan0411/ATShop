package com.atteam.atshop.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.atteam.atshop.model.Order;

public interface IOrderDAO extends JpaRepository<Order, Long>{
	@Query("SELECT o FROM Order o WHERE o.account.username=?1")
	List<Order> findByUsername(String username);
	
	
	// Admin: hiển thị các hóa đơn theo ngày chỉ định
	@Query(value = "select * from Orders where CreatedDate = ?1", nativeQuery = true)
	List<Order> getOrderByDay(String day);
}
