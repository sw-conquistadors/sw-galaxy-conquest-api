package com.conquest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.conquest.models.Game;

@Repository
public interface GameRepository extends JpaRepository<Game, Integer>{

}
