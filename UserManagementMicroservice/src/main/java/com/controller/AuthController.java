 package com.controller;


import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jwt.JwtUtility;
import com.model.Login;
import com.request.LoginRequest;
import com.response.JSONResponse;
import com.service.UserDetailsServiceImpl;

import jakarta.validation.Valid;



@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/app")
public class AuthController {
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private JwtUtility jwtUtility;
	
	@Autowired
	private UserDetailsServiceImpl service;
	
	@PostMapping("/signin")
	public ResponseEntity<?> validateUser(@Valid @RequestBody LoginRequest loginRequest) {
		
		Authentication authentication=authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(),loginRequest.getPassword()));
		
		SecurityContextHolder.getContext().setAuthentication(authentication);
		UserDetails userdetails= (UserDetails) authentication.getPrincipal();
		
		String token=jwtUtility.generateToken(authentication);
		
		Collection<? extends GrantedAuthority> authorities= userdetails.getAuthorities();
		List<String> list= authorities.stream().map(t -> t.getAuthority()).collect(Collectors.toList());
		
		JSONResponse resp=new JSONResponse(token, userdetails.getUsername(), list);
		return ResponseEntity.ok(resp);		
		
												
	}
	
	@PostMapping("/signup")
	public ResponseEntity<?> addUser(@RequestBody LoginRequest loginRequest) {
		Login user=new Login(loginRequest.getUsername(),loginRequest.getPassword(),"ROLE_USER");
		service.addUser(user);
		return ResponseEntity.ok(user);
		
	}
	
	@GetMapping("/checkUsername/{username}")
	public boolean checkUsername(@PathVariable String username) {
		
		return service.checkUsername(username);
		
	}
}
