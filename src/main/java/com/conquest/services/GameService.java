package com.conquest.services;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.conquest.models.Game;
import com.conquest.repository.GameRepository;

@Service
public class GameService {
	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	// this class is responsible to call the data layer and its CRUD methods
	@Autowired
	private GameRepository gameRepo;
	
	public Game findById(int id) {
		return gameRepo.getById(id);
	}
	
	public List<Game> findAll(){
		return gameRepo.findAll();
	}
	
	public Game add(Game game) {
		return gameRepo.save(game);
	}
	
	public void remove(int id) {
		gameRepo.delete(findById(id));
	}
	
	public Game update(Game game) {
		return gameRepo.save(game);
	}
}
