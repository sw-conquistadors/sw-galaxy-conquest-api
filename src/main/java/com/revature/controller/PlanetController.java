package com.revature.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.conquest.models.Planet;
import com.conquest.services.PlanetService;

@RestController
@RequestMapping("/planets")
public class PlanetController {
	
	
	@Autowired
	PlanetService planetServ;
	
	// a GET request to the above URL
	@GetMapping
	public ResponseEntity<List<Planet>> getAllPlanets() {
		return ResponseEntity.ok(planetServ.findAll()); 
	}
	
	@GetMapping("/find/{name}")
	public ResponseEntity<Planet> findByPlanetName(@PathVariable("name") String username) {
		
		return ResponseEntity.ok(planetServ.findByName(username));
	}
	
	// Think of how you implement the following methods
	
	// POST - add()
	@PostMapping("/add")
	public ResponseEntity<Planet> addPlanet(@Valid @RequestBody Planet u) { // valid annotation ensures that we can only accept a VALID user object
		// will return the newly added User object in JSON
		return ResponseEntity.ok(planetServ.add(u)); 
	}
	
	// GET - getById() - extract the id from the URI like in findByUsername();
	@GetMapping("/{id}")
	public ResponseEntity<Planet> findPlanetById(@PathVariable("id") int id) {
		
		return ResponseEntity.ok(planetServ.findById(id));
	}
	
	@DeleteMapping("/{id}")
	public void removePlanet(@PathVariable("id") int id) {
		planetServ.remove(id);
	}
}