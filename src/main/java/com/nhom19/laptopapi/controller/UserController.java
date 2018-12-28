package com.nhom19.laptopapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.nhom19.laptopapi.dto.LoginDto;
import com.nhom19.laptopapi.model.User;
import com.nhom19.laptopapi.service.UserService;

@RestController
public class UserController {
	@Autowired UserService userService;
	
	@RequestMapping(value = "/users", method = RequestMethod.GET)
	public ResponseEntity<List<User>> getUsers() {
		List<User> users = userService.getUsers();
		
		return new ResponseEntity<List<User>>(users, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/users/{id}", method=RequestMethod.GET)
	public ResponseEntity<User> getUserById(@PathVariable("id") int id)
	{
		User user = userService.getUserById(id);
		
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/register", method=RequestMethod.POST)
	public ResponseEntity<User> register(@RequestBody User user)
	{
		user = userService.Save(user);
		return new ResponseEntity<User>(user, HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/login", method=RequestMethod.POST)
	public ResponseEntity<Object> login(@RequestBody LoginDto loginDto)
	{
		String username = loginDto.getUsername();
		String password = loginDto.getPassword();
		
		User user = userService.login(username, password);
		
		if(user != null) {
			return new ResponseEntity<Object>(user, HttpStatus.OK);
		}
		return new ResponseEntity<>("Username or Password is incorrect",HttpStatus.NON_AUTHORITATIVE_INFORMATION);
	}
	
	@RequestMapping(value="/users/{id}/{newPassword}", method=RequestMethod.PUT)
	public ResponseEntity<Object> changePassword(@PathVariable("id") int id, @PathVariable("newPassword") String newPassword)
	{
		User user = userService.changePassword(id, newPassword);
		
		if(user == null)
		{
			return new ResponseEntity<Object>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<Object>(user, HttpStatus.OK);
	}
	
	@RequestMapping(value="/users", method=RequestMethod.PUT)
	public ResponseEntity<User> updateUser(@RequestBody User user)
	{
		user = userService.Save(user);
		
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}
	
	@RequestMapping(value="/users/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Object> deleteUser(@PathVariable("id") int id) 
	{		
		if(userService.deleteUser(id))
		{
			return new ResponseEntity<Object>("Delete success", HttpStatus.OK);
		}
		
		return new ResponseEntity<Object>("User is not exist", HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@RequestMapping(value="/users/state/{id}", method=RequestMethod.PUT)
	public ResponseEntity<Object> activeUser(@PathVariable("id") int id)
	{
		User user = userService.activeUser(id);
		
		if(user == null)
		{
			return new ResponseEntity<Object>("User is not exist", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<Object>(user, HttpStatus.OK);
	}
}
