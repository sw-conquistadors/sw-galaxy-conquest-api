package com.conquest.services;

import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.conquest.models.Planet;
import com.conquest.repository.PlanetRepository;

@Service
public class PlanetService {
	private static final int MAX_PLANETS = 60;

	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	// this class is responsible to calling the data layer and its CRUD methods
	@Autowired
	private PlanetRepository planetRepo;
	
	public Planet findById(int id) {
		return planetRepo.getById(id);
	}
	
	public Planet findByName(String name) {
		Optional<Planet> option = planetRepo.findByName(name);
		return option.isPresent() ? option.get() : null;
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
	
	public Planet makeApiCall(int num){ 
	    final String url = "https://swapi.dev/api/planets/" + num;

	    RestTemplate restTemplate = new RestTemplate();

	    String result = restTemplate.getForObject(url, String.class);

	    JSONObject jsonResponse = null;
		try {
			jsonResponse = new JSONObject(result);
	    } 
		catch (JSONException error) {
			error.printStackTrace();
	    }
	   
		try{
			int diameterVal;
			double gravityVal;
			long populationVal;
			
			Random random = new Random();
			
			//extract values from json data:
			String name = jsonResponse.getString("name");
			String terrain = jsonResponse.getString("terrain");
			String diameter = jsonResponse.getString("diameter");
			String population = jsonResponse.getString("population");
			String gravity = jsonResponse.getString("gravity");
			String climate = jsonResponse.getString("climate");
			
			// convert String values into numeric
			if(diameter.equalsIgnoreCase("unknown")) {
				diameterVal = random.nextInt(100000);
			}
			else {
				diameterVal = jsonResponse.getInt("diameter");
			}
			if(population.equalsIgnoreCase("unknown")) {
				populationVal = random.nextLong(350000000);
			}
			else {
				populationVal = jsonResponse.getInt("population");
			}
			if(gravity.equalsIgnoreCase("unknown")||gravity.equalsIgnoreCase("N/A")) {
				gravityVal = random.nextDouble(2.0);
			}
			else {
				String tempStr = jsonResponse.getString("gravity");
				String[] tempArr = tempStr.split(" ");
				gravityVal = Double.valueOf(tempArr[0]);
			}
			
			
			// return fully initialized obj
			return new Planet(url, name, terrain, diameterVal, populationVal, gravityVal, climate);
	    }
	   catch(JSONException error) 
	   {
		   error.printStackTrace();
		   // return nothing
		   return null;
	   	}

	}
	
	public void addAllPlanetsToDB() {
		for(int index = 1; index<=MAX_PLANETS;index++ ) {
			add(makeApiCall(index));
		}
	}

}
