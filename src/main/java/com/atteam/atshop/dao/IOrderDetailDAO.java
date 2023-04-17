package com.atteam.atshop.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.atteam.atshop.model.OrderDetail;

public interface IOrderDetailDAO extends JpaRepository<OrderDetail, Long>{

}
