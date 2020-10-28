package com.ctrends.model;

public class JwtResponse {
	
public String token;
	
	public String date;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}
	
	public JwtResponse(String token, String date) {
		super();
		this.token = token;
		this.date = date;
	}
    
}
