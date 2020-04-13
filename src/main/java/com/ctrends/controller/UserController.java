package com.ctrends.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ctrends.dao.IUserDao;
import com.ctrends.model.MyUser;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	IUserDao userDao;
	
	@GetMapping("/all")
	public List<MyUser> getAllUser(){
		return userDao.findAll();
	}
	
	@PostMapping(path="/add", consumes= "application/json", produces= "application/json")
	MyUser addUser(@RequestBody MyUser users) {
		users.setPassword(hashPassword(users.getPassword()));		
	  return userDao.save(users);
	}
	
	@PostMapping(path="/check", consumes= "application/json", produces= "application/json")
	MyUser checkUser(@RequestBody MyUser users) {
		
		System.out.println(users.getUsername()+" -----------------------------===================--------------------");
	MyUser user = userDao.getUserByUsername(users.getUsername());
	
	//checkPass("123456", user.getPassword());
	return user;
	
	}
	
	
	private String hashPassword(String plainTextPassword){
		return BCrypt.hashpw(plainTextPassword, BCrypt.gensalt());
	}
	
	private void checkPass(String plainPassword, String hashedPassword) {
		if (BCrypt.checkpw(plainPassword, hashedPassword))
			System.out.println("The password matches.");
		else
			System.out.println("The password does not match.");
	}
}
