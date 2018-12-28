package com.nhom19.laptopapi.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.nhom19.laptopapi.model.Order;
import com.nhom19.laptopapi.model.User;

@Repository
public interface OrderDao extends JpaRepository<Order, Integer> 
{
	@Query("SELECT o FROM Order o WHERE o.date = :date")
	public List<Order> getOrderByDate(@Param("date") Date date);
	
	@Query("SELECT o FROM Order o WHERE o.user = :user")
	public List<Order> getOrderByUser(@Param("user") User user);
}
