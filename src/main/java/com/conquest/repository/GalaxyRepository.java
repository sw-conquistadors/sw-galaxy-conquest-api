package com.conquest.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.conquest.models.Galaxy;

@Repository
public interface GalaxyRepository extends JpaRepository<Galaxy, Integer>{ 
	
}
