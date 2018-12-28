package com.nhom19.laptopapi.dto;

import java.util.Date;
import java.util.List;

import com.nhom19.laptopapi.model.CartDetail;
import com.nhom19.laptopapi.model.User;

public class CartDto {
	private int id;
	private Date date;
	private User user;
	private List<CartDetail> cartDetails;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	public List<CartDetail> getCartDetails() {
		return cartDetails;
	}
	public void setCartDetails(List<CartDetail> cartDetails) {
		this.cartDetails = cartDetails;
	}
}
