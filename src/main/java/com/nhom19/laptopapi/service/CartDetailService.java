package com.nhom19.laptopapi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nhom19.laptopapi.dao.CartDetailDao;
import com.nhom19.laptopapi.model.CartDetail;

@Service
@Transactional
public class CartDetailService {
	@Autowired CartDetailDao cartDetailDao;
	
	public CartDetail save(CartDetail cartDetail)
	{
		return cartDetailDao.save(cartDetail);
	}
	
	public List<CartDetail> saveAll(List<CartDetail> cartDetails)
	{
		return cartDetailDao.saveAll(cartDetails);
	}
	
	public List<CartDetail> getCartDetailsByCartId(int id)
	{
		return cartDetailDao.getCartDetailsByCartId(id);
	}
}
