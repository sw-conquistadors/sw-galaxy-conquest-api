package com.conquest.services;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.conquest.models.Galaxy;
import com.conquest.models.Planet;
import com.conquest.repository.PlanetRepository;

@Service
public class PlanetService {
	private static final int MAX_PLANETS = 60;

	private static final int TIER_TWO_MAX = 19;

	private static final int TIER_ONE_MAX = 17;

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
	
	public void remove(int id) {
		planetRepo.delete(findById(id));
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
			
			//extract values from json data:
			String name = jsonResponse.getString("name");
			String terrain = jsonResponse.getString("terrain");
			String diameter = jsonResponse.getString("diameter");
			String population = jsonResponse.getString("population");
			String gravity = jsonResponse.getString("gravity");
			String climate = jsonResponse.getString("climate");
			
			// convert String values into numeric
			if(diameter.equalsIgnoreCase("unknown")) { // remove unknown diameters
				diameterVal = ThreadLocalRandom.current().nextInt(100_000); // generate a random diameter
			}
			// diameter must be numeric and greater than zero
			else if(!diameter.equalsIgnoreCase("unknown") 
					&& jsonResponse.getInt("diameter") <= 0) {
				diameterVal = ThreadLocalRandom.current().nextInt(100_000); // generate a random diameter
			}
			// get the valid diameter 
			else {
				diameterVal = jsonResponse.getInt("diameter");
			}
			if(population.equalsIgnoreCase("unknown")) {
				populationVal = ThreadLocalRandom.current().nextLong(350_000_000); // generate a random population
			}
			else {
				populationVal = jsonResponse.getInt("population");
			}
			if(gravity.equalsIgnoreCase("unknown")
				||gravity.equalsIgnoreCase("N/A")) {
				gravityVal = ThreadLocalRandom.current().nextDouble(2.0);
			}
			else {
				String tempStr = jsonResponse.getString("gravity");
				String[] tempArr = tempStr.split(" ");
				gravityVal = Double.valueOf(tempArr[0]);
			}
			
			// From the base API values, calculate the recruitment and factory values for each planet
			
			int recruitment = 1;
			long temp = populationVal;
			while (temp > 10) {
				 recruitment ++;
				 temp /= 10;
			}
			
			int factory = 1;
			int tempDiameter = diameterVal;
			double tempGravity = gravityVal;
			while (tempDiameter > 10) {
			  factory ++;
			  tempDiameter = tempDiameter/10;
			}
			while (tempGravity > 0) {
			  factory ++;
			  tempGravity -= .25;
			}
			 
			int tier;
			int average = recruitment + factory;
			if(average>TIER_TWO_MAX) {
				tier = 1;
			}
			else if(average>TIER_ONE_MAX) {
				tier = 2;
			}
			else {
				tier = 3;
			}
			
			average /= 2;
			
			String image = "image/" + 0 + ".png";
			//System.out.println(image);
			// return fully initialized object
			return new Planet(url, image, gravityVal, climate, name, terrain, tier,
					average, recruitment, factory, diameterVal, populationVal, new HashSet<Galaxy>());
	    }
	   catch(JSONException error) 
	   {
		   error.printStackTrace();
		   return null;
	   	}

	}
	
	public void addAllPlanetsToDB() {
		for(int index = 1; index<=MAX_PLANETS;index++ ) {
			add(makeApiCall(index));
		}
	}
}
