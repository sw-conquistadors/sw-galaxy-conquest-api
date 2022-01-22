package com.conquest.controller;

import java.io.IOException;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.conquest.models.Planet;
import com.conquest.models.User;
import com.conquest.services.PlanetService;

import lombok.var;

@RestController
@RequestMapping("/planets")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class PlanetController {
	
	
	@Autowired
	PlanetService planetServ;
	
	// a GET request to the above URL
	@GetMapping
	public ResponseEntity<List<Planet>> getAllPlanets() {
		return ResponseEntity.ok(planetServ.findAll()); 
	}
	
	@GetMapping("/find/{name}") 
	public ResponseEntity<?> findByPlanetName(@PathVariable("name") String name) {
		Planet returnedPlanet;
		if((returnedPlanet = planetServ.findByName(name)) != null) {
			return ResponseEntity.ok()
	                    .body(returnedPlanet);
		}
		return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
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
	
//	@GetMapping("/assets/{id}")
//	public String getImage(@PathVariable("id") int id) {
//		
//		return planetServ.findById(id).getImage();
//	}
    @RequestMapping(value = "/image/{name}", method = RequestMethod.GET,
            produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<byte[]> getImage(@PathVariable("name") String name) throws IOException {
    	Planet returnedPlanet = planetServ.findByName(name);
    	String image = returnedPlanet.getImage();
        var imgFile = new ClassPathResource(image);
        byte[] bytes = StreamUtils.copyToByteArray(imgFile.getInputStream());

        return ResponseEntity
                .ok()
                .contentType(MediaType.IMAGE_PNG)
                .body(bytes);
    }
}