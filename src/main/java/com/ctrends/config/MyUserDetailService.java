package com.ctrends.config;

import java.util.ArrayList;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailService implements UserDetailsService {
	 @Override
	    public UserDetails loadUserByUsername(String userName){
	        return new User("abc","123",new ArrayList<>());
	    }
}
