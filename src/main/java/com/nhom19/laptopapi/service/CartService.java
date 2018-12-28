package com.nhom19.laptopapi.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.validator.constraints.SafeHtml.Attribute;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nhom19.laptopapi.dao.CartDao;
import com.nhom19.laptopapi.dto.CartDto;
import com.nhom19.laptopapi.dto.OrderDto;
import com.nhom19.laptopapi.model.Cart;
import com.nhom19.laptopapi.model.CartDetail;
import com.nhom19.laptopapi.model.Order;
import com.nhom19.laptopapi.model.OrderDetail;
import com.nhom19.laptopapi.model.User;

@Service
@Transactional
public class CartService {
	@Autowired private CartDao cartDao;
	@Autowired private CartDetailService cartDetailService;
	@Autowired private UserService userService;
	@Autowired private OrderService orderService;
	@Autowired private OrderDetailService orderDetailService;
	
	public List<CartDto> getCarts() 
	{
		List<CartDto> cartDtos = new ArrayList<>();
		List<Cart> carts = cartDao.findAll();
		
		for(Cart cart : carts)
		{
			CartDto cartDto = new CartDto();
			List<CartDetail> cartDetails = cartDetailService.getCartDetailsByCartId(cart.getId());
			
			cartDto.setId(cart.getId());
			cartDto.setDate(cart.getDate());
			cartDto.setUser(cart.getUser());
			cartDto.setCartDetails(cartDetails);
			
			cartDtos.add(cartDto);
		}
		
		return cartDtos;
	}
	
	public CartDto getCartById(int id) 
	{
		Cart cart = cartDao.findById(id).get();
		
		CartDto cartDto = new CartDto();
		List<CartDetail> cartDetails = cartDetailService.getCartDetailsByCartId(cart.getId());
			
		cartDto.setId(cart.getId());
		cartDto.setDate(cart.getDate());
		cartDto.setUser(cart.getUser());
		cartDto.setCartDetails(cartDetails);
		
		return cartDto;
	}
	
	public CartDto Save(CartDto cartDto)
	{
		Cart cart = new Cart();
		List<CartDetail> cartDetails = new ArrayList<>();
		
		cart.setId(cartDto.getId());
		cart.setDate(cartDto.getDate());
		cart.setUser(cartDto.getUser());
		cart = cartDao.save(cart);
		
		cartDetails = cartDto.getCartDetails();
		for(int i = 0; i < cartDetails.size(); i++)
		{
			cartDetails.get(i).setCart(cart.getId());
		}
		cartDetails = cartDetailService.saveAll(cartDetails);
		
		cartDto.setId(cart.getId());
		cartDto.setCartDetails(cartDetails);
		
		return cartDto;
	}
	
	public List<CartDto> getCartsByDate(Date date)
	{
		List<CartDto> cartDtos = new ArrayList<>();
		List<Cart> carts = cartDao.getCartsByDate(date);
		for(Cart cart : carts)
		{
			CartDto cartDto = new CartDto();
			List<CartDetail> cartDetails = cartDetailService.getCartDetailsByCartId(cart.getId());
			
			cartDto.setId(cart.getId());
			cartDto.setDate(cart.getDate());
			cartDto.setUser(cart.getUser());
			cartDto.setCartDetails(cartDetails);
			
			cartDtos.add(cartDto);
		}
		
		return cartDtos;
	}
	
	public List<CartDto> getCartsByUserId(int userId)
	{
		List<CartDto> cartDtos = new ArrayList<>();
		User user = userService.getUserById(userId);
		List<Cart> carts = cartDao.getCartsByUser(user);
		for(Cart cart : carts)
		{
			CartDto cartDto = new CartDto();
			List<CartDetail> cartDetails = cartDetailService.getCartDetailsByCartId(cart.getId());
			
			cartDto.setId(cart.getId());
			cartDto.setDate(cart.getDate());
			cartDto.setUser(cart.getUser());
			cartDto.setCartDetails(cartDetails);
			
			cartDtos.add(cartDto);
		}
		
		return cartDtos;
	}
	
	public OrderDto paymentCart(CartDto cartDto)
	{
		OrderDto orderDto = new OrderDto();
		List<OrderDetail> orderDetails = new ArrayList<>();
		
		orderDto.setDate(cartDto.getDate());
		orderDto.setType(1);
		orderDto.setUser(cartDto.getUser());
		
		for(CartDetail cartDetail : cartDto.getCartDetails())
		{
			OrderDetail orderDetail = new OrderDetail();
			orderDetail.setAmount(cartDetail.getAmount());
			orderDetail.setProduct(cartDetail.getProduct());
			orderDetail.setTotalPrice(cartDetail.getTotalPrice());
			
			orderDetails.add(orderDetail);
		}
		
		orderDto.setOrderDetails(orderDetails);
		
		orderDto = orderService.save(orderDto);
		
		return orderDto;
	}
}
