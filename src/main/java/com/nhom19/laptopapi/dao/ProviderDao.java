package com.nhom19.laptopapi.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.nhom19.laptopapi.model.Product;
import com.nhom19.laptopapi.model.Provider;

@Repository
public interface ProviderDao extends JpaRepository<Provider, Integer> 
{
	@Query("SELECT p FROM Product p WHERE p.provider = :provider")	
	public List<Product> getProductsByProvider(@Param("provider") Provider provider);
	
	@Query("Select p FROM Provider p WHERE p.name = :name")
	public List<Provider> getProviderByName(@Param("name") String name);
}
