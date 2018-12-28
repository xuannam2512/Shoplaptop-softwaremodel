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

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.nhom19.laptopapi.dto.OrderDto;
import com.nhom19.laptopapi.model.User;
import com.nhom19.laptopapi.service.OrderService;

@RestController
public class OrderController {
	@Autowired private OrderService orderService;
	
	@RequestMapping(value="/orders", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> getOrders()
	{
		List<OrderDto> orderDtos = orderService.getOrders();
		
		return new ResponseEntity<Object>(orderDtos, HttpStatus.ACCEPTED);
	}
	
	@RequestMapping(value="/orders/{id}", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> getOrderById(@PathVariable("id") int id)
	{
		OrderDto orderDto = orderService.getOrderById(id);
		
		return new ResponseEntity<Object>(orderDto, HttpStatus.OK);
	}
	
	@RequestMapping(value="/orders", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE, consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> createOrder(@RequestBody OrderDto orderDto)
	{
		orderDto.setDate(new Date());
		orderDto = orderService.save(orderDto);
		
		return new ResponseEntity<Object>(orderDto, HttpStatus.OK);
	}
		
	@RequestMapping(value="/orders/", method=RequestMethod.GET, consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> getOrderByDate(@RequestParam("date") String date) throws ParseException
	{
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date dateFilter = dateFormat.parse(date);
		List<OrderDto> orderDtos = orderService.getOrdersByDate(dateFilter);
		
		return new ResponseEntity<Object>(orderDtos, HttpStatus.OK);
	}
	
	@RequestMapping(value="/orders/user/{id}", method=RequestMethod.GET, consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> getOrderByUser(@PathVariable("id") int userId)
	{
		List<OrderDto> orderDtos = orderService.getOrdersByUser(userId);
		
		return new ResponseEntity<Object>(orderDtos, HttpStatus.OK);
	}
	
	@RequestMapping(value="/orders", method=RequestMethod.PUT, produces=MediaType.APPLICATION_JSON_VALUE, consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> updateOrder(@RequestBody OrderDto orderDto)
	{
		orderDto = orderService.save(orderDto);
		
		return new ResponseEntity<Object>(orderDto, HttpStatus.OK);
	}
}
