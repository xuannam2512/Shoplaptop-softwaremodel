package com.nhom19.laptopapi.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.nhom19.laptopapi.model.CartDetail;

@Repository
public interface CartDetailDao extends JpaRepository<CartDetail, Integer> 
{
	@Query("SELECT cd FROM CartDetail cd WHERE cd.cart_id = :cart_id")
	public List<CartDetail> getCartDetailsByCartId(@Param("cart_id") int cart_id);
}
