package com.nhom19.laptopapi.dao;

import java.util.Date;
import java.util.List;

import javax.jws.soap.SOAPBinding.Use;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.nhom19.laptopapi.model.Cart;
import com.nhom19.laptopapi.model.User;

@Repository
public interface CartDao extends JpaRepository<Cart, Integer> 
{
	@Query("SELECT c FROM Cart c WHERE c.date = :date")
	public List<Cart> getCartsByDate(@Param("date") Date date);
	
	@Query("SELECT c FROM Cart c WHERE c.user = :user")
	public List<Cart> getCartsByUser(@Param("user") User user);
}
