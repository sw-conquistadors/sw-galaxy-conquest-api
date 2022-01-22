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
	public ResponseEntity<List<?>> getAllUsers() {
		List<User> users = userServ.findAll();
		if(!users.isEmpty()) {
			return ResponseEntity.ok()
                    .body(users);
		}
		
		// Spring Boot web starter has Jackson Object Mapper automatically built in so this will be returned as JSON
		return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
	}
	
	@GetMapping("/find/{username}") 
	public ResponseEntity<?> findByUsername(@PathVariable("username") String username) {
		User returnedUser;
		if((returnedUser = userServ.findByUsername(username)) != null) {
			return ResponseEntity.ok()
	                    .body(returnedUser);
		}
		return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
	}
	
	// POST - add()
	@PostMapping("/add")
	public ResponseEntity<?> addUser(@Valid @RequestBody User u) { // valid annotation ensures that we can only accept a VALID user object
		// will return the newly added User object in JSON
		User returnedUser;
		if((returnedUser = userServ.add(u)) != null) {
			return ResponseEntity.ok()
	                    .body(returnedUser);
		}
		return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
	}
	
	
	// GET - getById() - extract the id from the URI like in findByUsername();
	@GetMapping("/{id}")
	public ResponseEntity<?> findUserById(@PathVariable("id") int id) {
		User returnedUser;
		if((returnedUser = userServ.findById(id)) != null) {
			return ResponseEntity.ok()
	                    .body(returnedUser);
		}
		return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> removeUser(@PathVariable("id") int id) {
		User returnedUser;
		if((returnedUser = userServ.remove(id)) != null) {
			return ResponseEntity.ok()
	                    .body(returnedUser);
		}
		return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
	}
	
	@GetMapping("/validate/{username}")  
	public ResponseEntity<?> validate(@PathVariable("username") String username, @PathVariable("password") String password) {
		User validUser;
		if((validUser = userServ.validate(username, password)) != null) {
			return ResponseEntity.ok()
                    .body(validUser);
		}
		return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
	}
}

