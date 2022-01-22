package com.conquest.controller;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.conquest.models.Game;
import com.conquest.services.GameService;

@RestController
@RequestMapping("/games")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class GameController {
	
	@Autowired
	GameService gameServ;
	
	// a GET request to the above URL
	@GetMapping
	public ResponseEntity<List<Game>> getAllGames() {
		return ResponseEntity.ok(gameServ.findAll()); 
	}
	
	// POST - add()
	@PostMapping("/add")
	public ResponseEntity<Game> addGame(@Valid @RequestBody Game game) { // valid annotation ensures that we can only accept a VALID user object
		// will return the newly added User object in JSON
		return ResponseEntity.ok(gameServ.add(game)); 
	}
	
	// GET - getById() - extract the id from the URI like in findByUsername();
	@GetMapping("/{id}")
	public ResponseEntity<Game> findGameById(@PathVariable("id") int id) {
		
		return ResponseEntity.ok(gameServ.findById(id));
	}
	
	@DeleteMapping("/{id}")
	public void removeGame(@PathVariable("id") int id) {
		gameServ.remove(id);
	}
}