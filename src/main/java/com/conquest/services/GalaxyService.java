package com.conquest.services;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.conquest.models.Galaxy;
import com.conquest.models.Planet;
import com.conquest.repository.GalaxyRepository;

@Service
public class GalaxyService {
	
	@Autowired
	private GalaxyRepository gRepo;
	
	public Galaxy add(Galaxy galaxy) {
		return gRepo.save(galaxy);
	}
	
	public Galaxy findById(int id) {
		return gRepo.getById(id);
	}
	
	public Set<Planet> showPlanets(int id) {
		Galaxy targetGalaxy = gRepo.getById(id);
		
		if (targetGalaxy != null && targetGalaxy.getPlanets() != null && !targetGalaxy.getPlanets().isEmpty()) {
			return targetGalaxy.getPlanets();
		}
		return new HashSet<Planet>();
	}
}
