package com.conquest.services;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.conquest.models.User;
import com.conquest.repository.UserRepository;

@Service
public class UserService {
	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	// this class is responsible to call the data layer and its CRUD methods
	@Autowired
	private UserRepository userRepo;
	
	public Optional<User> findById(int id) {
		return Optional.of(userRepo.getById(id));
	}
	
	public List<User> findAll(){
		return userRepo.findAll();
	}
	
	public User add(User user) {
		return userRepo.save(user);
	}
	
	public User remove(int id) {
		Optional<User> data = findById(id); 
		User removedUser = data.isPresent() ? data.get() : null; 
		if (removedUser != null) {
			userRepo.delete(removedUser);
		}
		return removedUser;			
	}
	
	public User update(User user) {
		return userRepo.save(user);
	}
	
	public User findByUsername(String name) {
		Optional<User> option = userRepo.findByUsername(name);
		return option.isPresent() ? option.get() : null;
	}
	
}
