package com.conquest.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.conquest.models.Planet;

@Repository
public interface PlanetRepository extends JpaRepository<Planet, Integer>{ 
	//Crud methods
	Optional<Planet> findByName(String name);
	
}
