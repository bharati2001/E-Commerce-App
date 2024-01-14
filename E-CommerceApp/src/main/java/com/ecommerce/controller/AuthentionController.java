package com.ecommerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.authentication.AuthenticationManager;
import com.ecommerce.config.JwtService;
import com.ecommerce.entity.User;
import com.ecommerce.response.Response;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@CrossOrigin("*")
@RequestMapping("/api")
public class AuthentionController {
	
	@Autowired
	private HttpServletRequest httpRequest;
	
	@Autowired
	private JwtService jwtService;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@PostMapping("/auth/user")
	private ResponseEntity<?> authenticateAndGetToken(@RequestBody User user) {
		String remoteAddr = httpRequest.getRemoteAddr().toString();
		try {
			if (user.getUsername() != null && user.getPassword() != null && !user.getUsername().isEmpty()
					&& !user.getPassword().isEmpty()) {
				Authentication authenticate = authenticationManager.authenticate(
						new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
				if (authenticate.isAuthenticated()) {
					String token = jwtService.generateToken(user.getUsername());
					return ResponseEntity.ok(new Response("success", token));
				} else {
					return ResponseEntity.ok(new Response("error", "Invalid UserName or Password"));
				}
			} else {
				return ResponseEntity.ok(new Response("error", "Enter a valid UserName and Password"));
			}
		} catch (Exception e) {
			return ResponseEntity.ok(new Response("error", "Enter a valid UserName and Password"));
		}

	}
}
