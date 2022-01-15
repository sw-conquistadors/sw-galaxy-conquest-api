package com.conquest.services;

import java.util.List;

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
	
	public User findById(int id) {
		return userRepo.getById(id);
	}
	
	public List<User> findAll(){
		return userRepo.findAll();
	}
	
	public User add(User user) {
		return userRepo.save(user);
	}
	
	public void remove(User user) {
		userRepo.delete(user);
	}
	
	public User update(User user) {
		return userRepo.save(user);
	}
}
