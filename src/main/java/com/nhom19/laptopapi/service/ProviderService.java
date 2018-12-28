package com.nhom19.laptopapi.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nhom19.laptopapi.dao.ProviderDao;
import com.nhom19.laptopapi.dto.ProviderDto;
import com.nhom19.laptopapi.model.Product;
import com.nhom19.laptopapi.model.Provider;

@Service
@Transactional
public class ProviderService {
	@Autowired ProviderDao providerDao;
	
	public List<ProviderDto> getProviders() {
		List<ProviderDto> providerDtos  = new ArrayList<>();
		
		List<Provider> providers = providerDao.findAll();
		
		for(Provider provider : providers)
		{
			ProviderDto providerDto = new ProviderDto();
			
			List<Product> products = providerDao.getProductsByProvider(provider);
			
			providerDto.setId(provider.getId());
			providerDto.setName(provider.getName());
			providerDto.setInfo(provider.getInfo());
			providerDto.setProducts(products);
			
			providerDtos.add(providerDto);
		}		
		
		return providerDtos;
	}
	
	public ProviderDto getProviderById(int id)
	{
		ProviderDto providerDto = new ProviderDto();
		
		Provider provider = providerDao.findById(id).get();
		List<Product> products = providerDao.getProductsByProvider(provider);
		
		providerDto.setId(provider.getId());
		providerDto.setName(provider.getName());
		providerDto.setInfo(provider.getInfo());
		providerDto.setProducts(products);
		
		return providerDto;
	}
	
	public ProviderDto getProviderByName(String name)
	{
		ProviderDto providerDto = new ProviderDto();
		List<Provider> providers = providerDao.getProviderByName(name);
		Provider provider = null;
		if(providers.size() == 0)
		{
			return null;
		}
		
		provider = providers.get(0);
		List<Product> products = providerDao.getProductsByProvider(provider);
		
		providerDto.setId(provider.getId());
		providerDto.setName(provider.getName());
		providerDto.setInfo(provider.getInfo());
		providerDto.setProducts(products);
		
		return providerDto;
	}
	
	public List<Product> getProductsByProvider(int id)
	{
		Provider provider = providerDao.findById(id).get();
		
		List<Product> products = providerDao.getProductsByProvider(provider);
		
		return products;
	}
	
	public Provider save(Provider provider)
	{
		return providerDao.save(provider);
	}
}
