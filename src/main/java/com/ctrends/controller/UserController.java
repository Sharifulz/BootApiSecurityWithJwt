package com.ctrends.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
	MyUser addNetworkSurvey(@RequestBody MyUser users) {
	  return userDao.save(users);
	}
}
