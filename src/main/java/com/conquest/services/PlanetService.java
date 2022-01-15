package com.conquest.services;

import java.util.List;

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
	
	public Planet findById(int id) {
		return planetRepo.getById(id);
	}
	
	public List<Planet> findAll(){
		return planetRepo.findAll();
	}
	
	public Planet add(Planet planet) {
		return planetRepo.save(planet);
	}
	
	public void remove(Planet planet) {
		planetRepo.delete(planet);
	}
	
	public Planet update(Planet planet) {
		return planetRepo.save(planet);
	}

}
