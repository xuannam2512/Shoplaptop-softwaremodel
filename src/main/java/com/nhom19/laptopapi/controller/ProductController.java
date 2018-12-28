package com.nhom19.laptopapi.controller;

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

import com.nhom19.laptopapi.model.Product;
import com.nhom19.laptopapi.service.ProductService;

@RestController
public class ProductController {
	
	@Autowired ProductService productService;
	
	@RequestMapping(value="/products", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> getProducts() 
	{
		List<Product> products = productService.getProducts();
		
		return new ResponseEntity<Object>(products, HttpStatus.OK);
	}
	
	@RequestMapping(value="/products/{id}", method=RequestMethod.GET)
	public ResponseEntity<Object> getProductById(@PathVariable("id") int id)
	{
		Product product = productService.getProductById(id);
		
		if(product == null)
		{
			return new ResponseEntity<Object>("Product is not exist", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<Object>(product, HttpStatus.OK);
	}
	
	@RequestMapping(value="/product", method=RequestMethod.GET)
	public ResponseEntity<Object> getProductByName(@RequestParam("name") String name)
	{
		Product product = productService.getProductByName(name);
		
		if(product == null) {
			return new ResponseEntity<Object>("Product is not exist", HttpStatus.INTERNAL_SERVER_ERROR);		
		}
		
		return new ResponseEntity<Object>(product, HttpStatus.OK);
	}
	
	@RequestMapping(value="/products", method=RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> createProduct(@RequestBody Product product)
	{
		product = productService.save(product);
		
		return new ResponseEntity<Object>(product, HttpStatus.OK);
	}
	
	@RequestMapping(value="/products", method=RequestMethod.PUT)
	public ResponseEntity<Object> updateProduct(@RequestBody Product product)
	{
		product = productService.save(product);
		
		return new ResponseEntity<Object>(product, HttpStatus.OK);
	}
	
	@RequestMapping(value="/products/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Object> deleteProduct(@PathVariable("id") int id)
	{
		if(!productService.deleteProduct(id))
		{
			return new ResponseEntity<Object>("Product is not exist", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<Object>("Delete Success", HttpStatus.OK);
	}
}
