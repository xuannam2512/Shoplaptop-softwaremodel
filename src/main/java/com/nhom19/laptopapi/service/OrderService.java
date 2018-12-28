package com.nhom19.laptopapi.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nhom19.laptopapi.dao.OrderDao;
import com.nhom19.laptopapi.dto.OrderDto;
import com.nhom19.laptopapi.model.Order;
import com.nhom19.laptopapi.model.OrderDetail;
import com.nhom19.laptopapi.model.User;

@Service
@Transactional
public class OrderService {
	@Autowired OrderDao orderDao;
	@Autowired OrderDetailService orderDetailService;
	@Autowired UserService userService;
	
	public List<OrderDto> getOrders() 
	{
		List<OrderDto> orderDtos = new ArrayList<>();		
		List<Order> orders = orderDao.findAll();
		
		for(Order order : orders)
		{
			OrderDto orderDto = new OrderDto();
			List<OrderDetail> orderDetails = orderDetailService.getOrderDetailsByOrder(order);
			
			orderDto.setOrder_id(order.getOrder_id());
			orderDto.setDate(order.getDate());
			orderDto.setOrderDetails(orderDetails);
			orderDto.setType(order.getType());
			orderDto.setUser(order.getUser());
			
			orderDtos.add(orderDto);
		}
		
		return orderDtos;
	}
	
	public OrderDto getOrderById(int id)
	{
		OrderDto orderDto = new OrderDto();
		Order order = orderDao.findById(id).get();
		List<OrderDetail> orderDetails = orderDetailService.getOrderDetailsByOrder(order);
		
		orderDto.setOrder_id(order.getOrder_id());
		orderDto.setDate(order.getDate());
		orderDto.setOrderDetails(orderDetails);
		orderDto.setType(order.getType());
		orderDto.setUser(order.getUser());
		
		return orderDto;
	}
	
	public OrderDto save(OrderDto orderDto)
	{
		Order order = new Order();
		List<OrderDetail> orderDetails = new ArrayList<>();
		
		order.setDate(orderDto.getDate());
		order.setType(orderDto.getType());
		order.setUser(orderDto.getUser());
		order = orderDao.save(order);
		
		orderDetails = orderDto.getOrderDetails();
		for(int i = 0; i < orderDetails.size(); i++)
		{
			orderDetails.get(i).setOrder(order.getOrder_id());
		}
		orderDetails = orderDetailService.saveAll(orderDetails);
		
		orderDto.setOrder_id(order.getOrder_id());
		orderDto.setOrderDetails(orderDetails);
		
		return orderDto;
	}
	
	public List<OrderDto> getOrdersByDate(Date date)
	{
		List<OrderDto> orderDtos = new ArrayList<>();
		List<Order> orders = orderDao.getOrderByDate(date);
		
		for(Order order : orders)
		{
			OrderDto orderDto = new OrderDto();
			List<OrderDetail> orderDetails = orderDetailService.getOrderDetailsByOrder(order);
			
			orderDto.setOrder_id(order.getOrder_id());
			orderDto.setDate(order.getDate());
			orderDto.setOrderDetails(orderDetails);
			orderDto.setType(order.getType());
			orderDto.setUser(order.getUser());
			
			orderDtos.add(orderDto);
		}
		
		return orderDtos;
	}
	
	public List<OrderDto> getOrdersByUser(int userId)
	{
		List<OrderDto> orderDtos = new ArrayList<>();
		User user = userService.getUserById(userId);
		List<Order> orders = orderDao.getOrderByUser(user);
		
		for(Order order : orders)
		{
			OrderDto orderDto = new OrderDto();
			List<OrderDetail> orderDetails = orderDetailService.getOrderDetailsByOrder(order);
			
			orderDto.setOrder_id(order.getOrder_id());
			orderDto.setDate(order.getDate());
			orderDto.setOrderDetails(orderDetails);
			orderDto.setType(order.getType());
			orderDto.setUser(order.getUser());
			
			orderDtos.add(orderDto);
		}
		
		return orderDtos;
	}
}
