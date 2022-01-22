package com.conquest.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.conquest.models.User;
import com.conquest.services.UserService;

@RestController 
@RequestMapping("/users") 
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UserController {
	
	
	@Autowired
	UserService userServ;
	
	@GetMapping
	public ResponseEntity<List<User>> getAllUsers() {
		// Spring Boot web starter has Jackson Object Mapper automatically built in so this willbe returned as JSON
		return ResponseEntity.ok(userServ.findAll()); // findAll() from userService!
	}
	
	@GetMapping("/find/{username}")  // localhost:5000/users/find/spongebob <- we extract this parameter
	public ResponseEntity<User> findByUsername(@PathVariable("username") String username) {
		
		return ResponseEntity.ok(userServ.findByUsername(username));
	}
	
	// Think of how you implement the following methods
	
	// POST - add()
	@PostMapping("/add")
	public ResponseEntity<?> addUser(@Valid @RequestBody User u) { // valid annotation ensures that we can only accept a VALID user object
		// will return the newly added User object in JSON
//		return ResponseEntity.ok(userServ.add(u));
		User returnedUser;
		if((returnedUser = userServ.add(u)) != null) {
			return ResponseEntity.ok()
	                    .body(returnedUser);
		}
		return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
	}
	
//	@PostMapping("/add")
//	public ResponseEntity<?> registration(@RequestBody User user) {
//		if (userv.login(user.getEmail(), user.getPassword()) != null) {
//			userv.insertUser(user);
//			System.out.println(user.toString());
//			HttpHeaders headers = new HttpHeaders();
//	        headers.add(HttpHeaders.AUTHORIZATION, jws.tokenGenerator("email: " + user.getEmail() + ", pasword: " + user.getPassword()).serialize());
//			  return ResponseEntity.ok()
//	                    .headers(headers)
//	                    .body(null);
//		}
//		return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
//	}

	
	
	// GET - getById() - extract the id from the URI like in findByUsername();
	@GetMapping("/{id}")
	public ResponseEntity<User> findUserById(@PathVariable("id") int id) {
		
		return ResponseEntity.ok(userServ.findById(id));
	}
	
	@DeleteMapping("/{id}")
	public void removeUser(@PathVariable("id") int id) {
		userServ.remove(id);
	}
	
	@GetMapping("/validate/{username}")  
	public ResponseEntity<User> validate(@PathVariable("username") String username, @PathVariable("password") String password) {
		
		return ResponseEntity.ok(userServ.validate(username, password));
	}
}

