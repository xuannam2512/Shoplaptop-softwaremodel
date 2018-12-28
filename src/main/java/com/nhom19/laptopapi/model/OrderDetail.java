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
@Table(name="order_detail")
public class OrderDetail {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id")
	private int id;
	
	@OneToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="product_id")
	private Product product;
	
	@Column(name="amount")
	private int amount;
	
	@Column(name="total_price")
	private BigInteger totalPrice;
	
	@Column(name="order_id")
	private int order_id;

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

	public int getOrder() {
		return order_id;
	}

	public void setOrder(int order_id) {
		this.order_id = order_id;
	}
}
