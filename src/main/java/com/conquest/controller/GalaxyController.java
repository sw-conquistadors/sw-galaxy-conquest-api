package com.conquest.controller;

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
import com.conquest.services.GalaxyService;

@RestController
@RequestMapping("/galaxies")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class GalaxyController {

	@Autowired
	private GalaxyService gServ;

	// POST - add()
	@PostMapping("/add")
	public ResponseEntity<?> addGalaxy(@Valid @RequestBody Galaxy g) { // valid annotation ensures that we can only
																		// accept a VALID user object
		// will return the newly added User object in JSON
		Galaxy returnedGalaxy;
		if ((returnedGalaxy = gServ.add(g)) != null) {
			return ResponseEntity.ok().body(returnedGalaxy);
		}
		return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getGalaxy(@PathVariable("id") int id) {
		Galaxy galaxy;
		if((galaxy = gServ.findById(id)) != null) {
			System.out.println("Got the Galaxy =====================\n\n\n\n");
			GalaxyDTO galaxyDTO = new GalaxyDTO(galaxy);
			System.out.println("Created DTO ==================\n\n\n\n\n");
				return ResponseEntity.ok()
	                    .body(galaxyDTO.getPlanets());
		}
		return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		
	}
	
}
