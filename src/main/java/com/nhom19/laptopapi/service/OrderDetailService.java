package com.nhom19.laptopapi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nhom19.laptopapi.dao.OrderDetailDao;
import com.nhom19.laptopapi.model.Order;
import com.nhom19.laptopapi.model.OrderDetail;

@Service
@Transactional
public class OrderDetailService {
	@Autowired OrderDetailDao orderDetailDao;
	
	public List<OrderDetail> getOrderDetails()
	{
		List<OrderDetail> orderDetails = orderDetailDao.findAll();
		
		return orderDetails;
	}
	
	public OrderDetail getOrderDetailById(int id)
	{
		OrderDetail orderDetail = orderDetailDao.findById(id).get();
		
		return orderDetail;
	}
	
	public List<OrderDetail> getOrderDetailsByOrder(Order order)
	{
		List<OrderDetail> orderDetails = orderDetailDao.getOrderDetailByOrderId(order.getOrder_id());
		
		return orderDetails;
	}
	
	public OrderDetail save(OrderDetail orderDetail)
	{
		return orderDetailDao.save(orderDetail);
	}
	
	public List<OrderDetail> saveAll(List<OrderDetail> orderDetails)
	{		
		return orderDetailDao.saveAll(orderDetails);
	}
	
	public Boolean delete(int id)
	{
		OrderDetail orderDetail = orderDetailDao.findById(id).get();
		
		if(orderDetail == null)
		{
			return false;
		}
		
		orderDetailDao.deleteById(id);
		
		return true;
	}
}
