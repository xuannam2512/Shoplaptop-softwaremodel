package com.nhom19.laptopapi.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.nhom19.laptopapi.model.Product;

@Repository
public interface ProductDao extends JpaRepository<Product, Integer>
{
	@Query("SELECT p FROM Product p WHERE p.name = :name")
	public List<Product> getProductByName(@Param("name") String name);
}
