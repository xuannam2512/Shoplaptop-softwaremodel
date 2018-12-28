package com.nhom19.laptopapi.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.nhom19.laptopapi.model.User;

@Repository
public interface UserDao extends JpaRepository<User, Integer>
{
	@Query("Select u FROM User u WHERE u.username = :username")
	public List<User> getUserByUserName(@Param("username") String username);
}
