package com.ctrends.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	 @Autowired
	 private MyUserDetailService myUserDetailService;

	 @Autowired
	 private JwtFilters jwtFilters;
	 
	 @Override
     public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(myUserDetailService);
     }
	 
	 @Override
	    protected  void configure(HttpSecurity http) throws Exception {
	        http.csrf()
	                .disable()
	                .authorizeRequests()
	                .antMatchers("/admin").hasRole("ADMIN")
	    			.antMatchers("/user").hasAnyRole("ADMIN", "USER")
	    			.antMatchers("/").permitAll()
	    			.anyRequest()
	                .authenticated()
	                .and()
	                .sessionManagement()
	                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	        http.addFilterBefore(jwtFilters, UsernamePasswordAuthenticationFilter.class);
	    }


	    @Bean
	    public PasswordEncoder passwordEncoder(){
	        return NoOpPasswordEncoder.getInstance();
	    }
	    @Bean(name = BeanIds.AUTHENTICATION_MANAGER)
	    @Override
	    public AuthenticationManager authenticationManagerBean() throws Exception {
	        return super.authenticationManagerBean();
	    }
}
