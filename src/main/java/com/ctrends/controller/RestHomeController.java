package com.ctrends.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ctrends.config.JwtTokenService;
import com.ctrends.config.MyUserDetailService;
import com.ctrends.model.JwtRequest;
import com.ctrends.model.JwtResponse;

import io.jsonwebtoken.impl.DefaultClaims;

@RestController
public class RestHomeController {
	
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private MyUserDetailService UserDetailsService;
    @Autowired
    private JwtTokenService jwtUtil;

	/*
	 * @GetMapping("/") public String index(){ return "Api is Working."; }
	 */
    @SuppressWarnings("rawtypes")
	@PostMapping("/login")
    public ResponseEntity<?> authenticate(@RequestBody JwtRequest jwtRequest){
        try{
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(jwtRequest.getUserName(),jwtRequest.getPassword()));
        }catch (BadCredentialsException e){
            throw e;
        }
        UserDetails userDetails=UserDetailsService.loadUserByUsername(jwtRequest.getUserName());
        final String jwt = jwtUtil.generateToken(userDetails);
		final Date date = jwtUtil.getExpirationDateFromToken(jwt);
				
		System.out.println("Expired Date "+ date.toString());
		return ResponseEntity.ok(new JwtResponse(jwt,date.toString()));
    }
    
    @RequestMapping(value = "/refreshtoken", method = RequestMethod.GET)
	public ResponseEntity<?> refreshtoken(HttpServletRequest request) throws Exception {
		// From the HttpRequest get the claims
		DefaultClaims claims = (io.jsonwebtoken.impl.DefaultClaims) request.getAttribute("claims");

		Map<String, Object> expectedMap = getMapFromIoJsonwebtokenClaims(claims);
		String token = jwtUtil.doGenerateRefreshToken(expectedMap, expectedMap.get("sub").toString());
		final Date date = jwtUtil.getExpirationDateFromToken(token);
		return ResponseEntity.ok(new JwtResponse(token,date.toString() ));
	}

	public Map<String, Object> getMapFromIoJsonwebtokenClaims(DefaultClaims claims) {
		Map<String, Object> expectedMap = new HashMap<String, Object>();
		for (Entry<String, Object> entry : claims.entrySet()) {
			expectedMap.put(entry.getKey(), entry.getValue());
		}
		return expectedMap;
	}
    /*
    {
    	"userName":"ert",
    	"password":"789"
    }
    */
}

















