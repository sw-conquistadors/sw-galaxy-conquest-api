package com.conquest.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

//Planets are the players primary challenge in the game as well as the source of resources
//Planets may be made into factory worlds or recruiting worlds; each providing its own benefits
//Factory worlds improve the quality of the players army allowing it to deal more damage and take less
//Cloning worlds produce new soldiers for the army, giving it more men

//Planets have a front size determined by the API call, this determines how much of the players army can deploy
//Planets have various factors which determine how well they work as a factory, clone, or logistic world

//Each game contains 20 planets randomly determined by the API
@Data
@NoArgsConstructor
@Entity
@Table(name="planets")
public class Planet {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;// planet id 
	private String url; // the planet's url
	private String name;
	private String terrain;
	
	//The diameter of a planet determines how well it serves as a factory
	private String diameter;
	
	//The population of a planet determines how many troops can be recruited from it
	private String population;
	
	//The scope of a planet determines how many 
	private String gravity;
	
	private String climate;

	public Planet(String url, String name, String terrain, String diameter, String population, String gravity, String climate) {
		super();
		this.url = url;
		this.name = name;
		this.terrain = terrain;
		this.diameter = diameter;
		this.population = population;
		this.gravity = gravity;
		this.climate = climate;
	}
}

