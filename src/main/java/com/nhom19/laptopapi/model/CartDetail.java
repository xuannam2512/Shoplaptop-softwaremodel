package com.nhom19.laptopapi.model;

import java.math.BigInteger;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="cart_detail")
public class CartDetail {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="cart_detail_id")
	private int id;
	
	@OneToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="product_id")
	private Product product;
	
	@Column(name="amount")
	private int amount;
	
	@Column(name="total_price")
	private BigInteger totalPrice;
	
	@Column(name = "cart_id")
	private int cart_id;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public BigInteger getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(BigInteger totalPrice) {
		this.totalPrice = totalPrice;
	}

	public int getCart() {
		return cart_id;
	}

	public void setCart(int cart_id) {
		this.cart_id = cart_id;
	}
}
