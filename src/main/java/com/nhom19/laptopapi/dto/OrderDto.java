package com.nhom19.laptopapi.dto;

import java.util.Date;
import java.util.List;

import com.nhom19.laptopapi.model.OrderDetail;
import com.nhom19.laptopapi.model.User;

public class OrderDto {
	private int order_id;
	private Date date;
	private User user;	
	private int type;
	private List<OrderDetail> orderDetails;

	public int getOrder_id() {
		return order_id;
	}

	public void setOrder_id(int order_id) {
		this.order_id = order_id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public List<OrderDetail> getOrderDetails() {
		return orderDetails;
	}

	public void setOrderDetails(List<OrderDetail> orderDetails) {
		this.orderDetails = orderDetails;
	}	
}
