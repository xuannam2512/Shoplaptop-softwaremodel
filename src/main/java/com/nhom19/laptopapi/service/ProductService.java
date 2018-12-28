package com.nhom19.laptopapi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nhom19.laptopapi.dao.ProductDao;
import com.nhom19.laptopapi.model.Product;

@Service
@Transactional
public class ProductService {
	@Autowired ProductDao productDao;
	
	public List<Product> getProducts()
	{
		List<Product> products = productDao.findAll();
		
		return products;
	}
	
	public Product getProductById(int id) {
		Product product = productDao.findById(id).get();
		
		return product;
	}
	
	public Product save(Product product)
	{
		return productDao.save(product);
	}
	
	public Boolean deleteProduct(int id) 
	{
		Product product = productDao.findById(id).get();
		
		if(product == null)
		{
			return false;
		}
		
		productDao.deleteById(id);
		
		return true;
	}
	
	public Product getProductByName(String name) {
		List<Product> products = productDao.getProductByName(name);
		Product product = null;
		
		if(products.size() == 0)
		{
			return null;
		}
		
		product = products.get(0);
		
		return product;
	}	
}
