package com.conquest.controller;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.conquest.models.Galaxy;
import com.conquest.models.GalaxyDTO;
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
	public ResponseEntity<List<?>> getAllGames() {
		List<Game> games = gameServ.findAll();
		if(!games.isEmpty()) {
			return ResponseEntity.ok()
                    .body(games);
		}
		
		// Spring Boot web starter has Jackson Object Mapper automatically built in so this will be returned as JSON
		return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
	}
	
	// POST - add()
	@PostMapping("/add")
	public ResponseEntity<Game> addGame(@Valid @RequestBody Game game) { // valid annotation ensures that we can only accept a VALID user object
		// will return the newly added User object in JSON
		return ResponseEntity.ok(gameServ.add(game)); 
	}
	
	// GET - getById() - extract the id from the URI like in findByUsername();
	@GetMapping("/{id}")
	public ResponseEntity<?> findGameById(@PathVariable("id") int id) {
		Optional<Game> optionalGame;
		if((optionalGame = gameServ.findById(id)) != null) {
			Game returnedGame = optionalGame.isPresent() ? optionalGame.get() : null;
			return ResponseEntity.ok()
	                    .body(returnedGame);
		}
		return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
	}
	
	@GetMapping("/{id}/galaxy")
	public ResponseEntity<?> getAllPlanetsOfAGalaxy(@PathVariable("id") int id) {
		Galaxy galaxy;
		if((galaxy = gameServ.getGalaxy(id)) != null) {
			GalaxyDTO galaxyDTO = new GalaxyDTO(galaxy);
				return ResponseEntity.ok()
	                    .body(galaxyDTO.getPlanets());
		}
		return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
	}
	
	
	
	/*
	 * User returnedUser;
		if((returnedUser = userServ.add(u)) != null) {
			return ResponseEntity.ok()
	                    .body(returnedUser);
		}
		return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
	 */
}
