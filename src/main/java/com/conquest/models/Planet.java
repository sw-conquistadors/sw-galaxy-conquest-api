package com.conquest.models;

import javax.persistence.Column;
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
	@Column(unique=true, nullable=false)
	private String name;
	private String terrain;
	// these fields determine how effective a planet is at maintaining/improving the army
	private int recruitment;
	private int factory;
	//The diameter of a planet determines how well it serves as a factory
	private int diameter;
	
	//The population of a planet determines how many troops can be recruited from it
	private long population;
	
	//The gravity of a planet 
	@Column(scale=2)
	private double gravity;
	//The climate of a planet 
	private String climate;
	public Planet(String url, String name, String terrain, int recruitment, int factory, int diameter, long population,
			double gravity, String climate) {
		super();
		this.url = url;
		this.name = name;
		this.terrain = terrain;
		this.recruitment = recruitment;
		this.factory = factory;
		this.diameter = diameter;
		this.population = population;
		this.gravity = gravity;
		this.climate = climate;
	}


}

