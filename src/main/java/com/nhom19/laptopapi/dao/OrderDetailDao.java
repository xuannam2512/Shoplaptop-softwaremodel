package com.nhom19.laptopapi.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.nhom19.laptopapi.model.OrderDetail;

@Repository
public interface OrderDetailDao extends JpaRepository<OrderDetail, Integer> 
{
	@Query("SELECT od FROM OrderDetail od WHERE od.order_id = :order_id")
	public List<OrderDetail> getOrderDetailByOrderId(@Param("order_id") int order_id);
}
