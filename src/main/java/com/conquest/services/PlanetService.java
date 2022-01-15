package com.conquest.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.conquest.models.Planet;
import com.conquest.repository.PlanetRepository;

@Service
public class PlanetService {
	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	// this class is responsible to calling the data layer and its CRUD methods
	@Autowired
	private PlanetRepository planetRepo;
	
	public Planet add(Planet planet) {
		return planetRepo.save(planet);
	}
	

}
