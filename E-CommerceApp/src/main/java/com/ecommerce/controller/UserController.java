package com.ecommerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.entity.User;
import com.ecommerce.response.Response;
import com.ecommerce.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/createuser")
	public ResponseEntity<Response> createUser(@RequestBody User user){
		userService.createUser(user);
		Response response = new Response("success", "User Created Successfully!!");
	    return new ResponseEntity<>(response, HttpStatus.OK);
	}

}
