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

import com.nhom19.laptopapi.dao.ProviderDao;
import com.nhom19.laptopapi.dto.ProviderDto;
import com.nhom19.laptopapi.model.Product;
import com.nhom19.laptopapi.model.Provider;
import com.nhom19.laptopapi.service.ProviderService;

@RestController
public class ProviderController {

	@Autowired private ProviderService providerService;
	
	@RequestMapping(value="/providers", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> getProviders() 
	{
		List<ProviderDto> providerDtos = providerService.getProviders();
		
		return new ResponseEntity<Object>(providerDtos, HttpStatus.OK);
	}
	@RequestMapping(value="/providers/", method=RequestMethod.GET)
	public ResponseEntity<Object> getProviderByName(@RequestParam("name") String name)
	{
		ProviderDto providerDto = providerService.getProviderByName(name);
		
		return new ResponseEntity<Object>(providerDto, HttpStatus.OK);
	}
	
	@RequestMapping(value="/providers/{id}", method=RequestMethod.GET)
	public ResponseEntity<Object> getProviderById(@PathVariable("id") int id)
	{
		ProviderDto providerDto = providerService.getProviderById(id);
		
		return new ResponseEntity<Object>(providerDto, HttpStatus.OK);
	}	
	
	@RequestMapping(value="/providers/{id}/products", method=RequestMethod.GET)
	public ResponseEntity<Object> getProductsByProvider(@PathVariable("id") int id)
	{
		List<Product> products = providerService.getProductsByProvider(id);
		
		return new ResponseEntity<Object>(products, HttpStatus.OK);
	}
	
	@RequestMapping(value="providers", method=RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> createProvider(@RequestBody Provider provider)
	{
		provider = providerService.save(provider);
		
		return new ResponseEntity<Object>(provider, HttpStatus.OK);
	}
	
	@RequestMapping(value="/provider", method=RequestMethod.PUT)
	public ResponseEntity<Object> updateProvider(@RequestBody Provider provider)
	{
		provider = providerService.save(provider);
		
		return new ResponseEntity<Object>(provider, HttpStatus.OK);
	}
}
