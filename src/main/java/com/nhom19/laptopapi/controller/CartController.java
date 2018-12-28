package com.nhom19.laptopapi.controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nhom19.laptopapi.dto.CartDto;
import com.nhom19.laptopapi.dto.OrderDto;
import com.nhom19.laptopapi.service.CartService;

@RestController
public class CartController {

	@Autowired private CartService cartService;
	
	@RequestMapping(value="/carts", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> getCarts()
	{
		List<CartDto> cartDtos = cartService.getCarts();
		
		return new ResponseEntity<Object>(cartDtos, HttpStatus.OK);
	}
	
	@RequestMapping(value="/carts/{id}", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> getCartById(@PathVariable("id") int id)
	{
		CartDto cartDto = cartService.getCartById(id);
		
		return new ResponseEntity<Object>(cartDto, HttpStatus.OK);
	}
	
	@RequestMapping(value="/carts", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE, consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> createCart(@RequestBody CartDto cartDto)
	{
		cartDto.setDate(new Date());
		cartDto = cartService.Save(cartDto);
		
		return new ResponseEntity<Object>(cartDto, HttpStatus.OK);
	}
	
	@RequestMapping(value="/carts", method=RequestMethod.PUT, produces=MediaType.APPLICATION_JSON_VALUE, consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> updateCart(@RequestBody CartDto cartDto)
	{		
		cartDto = cartService.Save(cartDto);
		
		return new ResponseEntity<Object>(cartDto, HttpStatus.OK);
	}
	
	@RequestMapping(value="/carts/users/{id}", method=RequestMethod.GET)
	public ResponseEntity<Object> getCartByUserId(@PathVariable("id") int userId)
	{
		List<CartDto> cartDtos = cartService.getCartsByUserId(userId);
		
		return new ResponseEntity<Object>(cartDtos, HttpStatus.OK);
	}
	
	@RequestMapping(value="/carts/", method=RequestMethod.GET)
	public ResponseEntity<Object> getCartByDate(@RequestParam("date") String date) throws ParseException
	{
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date dateFilter = dateFormat.parse(date);
		
		List<CartDto> cartDtos = cartService.getCartsByDate(dateFilter);
		
		return new ResponseEntity<Object>(cartDtos, HttpStatus.OK);
	}
	
	@RequestMapping(value="/carts/payments", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE, consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> payCart(@RequestBody CartDto cartDto)
	{
		OrderDto orderDto = cartService.paymentCart(cartDto);
		
		return new ResponseEntity<Object>(orderDto, HttpStatus.OK);
	}
}
