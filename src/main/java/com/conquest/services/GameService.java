package com.conquest.services;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.conquest.models.Galaxy;
import com.conquest.models.Game;
import com.conquest.repository.GameRepository;

@Service
public class GameService {
	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	// this class is responsible to call the data layer and its CRUD methods
	@Autowired
	private GameRepository gameRepo;
	
	public Optional<Game> findById(int id) {
		return Optional.of(gameRepo.getById(id));
	}
	
	public List<Game> findAll(){
		return gameRepo.findAll();
	}
	
	public Game add(Game game) {
		return gameRepo.save(game);
	}
	
	public Game remove(int id) {
		Optional<Game> data = findById(id); 
		Game removedGame = data.isPresent() ? data.get() : null; 
		if (removedGame != null) {
			gameRepo.delete(removedGame);
		}
		return removedGame;			
	}
	
	public Game update(Game game) {
		return gameRepo.save(game);
	}
	
	public Galaxy getGalaxy(int id) {
		return gameRepo.getById(id).getGalaxy();
	}
	
//	public Galaxy getPlanets(int id) {
//		Game currentGame = gameRepo.getById(id);
//		Galaxy currentGalaxy = currentGame.getGalaxy();
//		return currentGalaxy.getPlanets();
//	}
}
