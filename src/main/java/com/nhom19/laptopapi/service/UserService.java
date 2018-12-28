package com.nhom19.laptopapi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nhom19.laptopapi.dao.UserDao;
import com.nhom19.laptopapi.model.User;

@Service
@Transactional
public class UserService {

	@Autowired
	private UserDao userDao;
	
	public User login(String username, String password) {
		List<User> users = userDao.getUserByUserName(username);
		User user = null;
		
		if(users.size() != 0) {
			user = users.get(0);
		} else {
			return null;
		}
		
		if(user.getPassword().equals(password)) {
			return user;
		}
		
		return null;
	}
	
	public User Save(User user) {
		
		return userDao.save(user);
	}
	
	public List<User> getUsers() {
		return userDao.findAll();
	}
	
	public User getUserById(int id)
	{		
		return userDao.findById(id).get();
	}
	
	public User changePassword(int id, String newPassword)
	{
		User user = userDao.findById(id).get();
		
		if(user == null) {
			return null;
		}
		
		user.setPassword(newPassword);
		
		return userDao.save(user);
	}
	
	public Boolean deleteUser(int id)
	{
		User user = userDao.findById(id).get();
		
		if(user == null)
		{
			return false;
		}
		
		userDao.deleteById(id);
		
		return true;
	}
	
	public User activeUser(int id)
	{
		User user = userDao.findById(id).get();
		
		if(user == null)
		{
			return null;
		}
		
		user.setState(1);
		
		return userDao.save(user);
	}
}
