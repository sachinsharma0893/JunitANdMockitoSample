package com.example.multitenancy.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.multitenancy.dto.UserDto;

@RestController
@RequestMapping(value = { "api/v1/user" })
public class UserController {

	@Autowired
	private UserDetailsService userDetailsService;

	@PostMapping
	public ResponseEntity<String> getValidUser(UserDto userDto) {
		System.out.print("*************  Login call executed  ****************");
		userDetailsService.loadUserByUsername(userDto.getUsername());
		return new ResponseEntity<String>("Logged-in Successfull", HttpStatus.OK);
	}

}
