package com.conquest.models;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.conquest.services.JsonViewProfiles;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

//Planets are the players primary challenge in the game as well as the source of resources
//Planets may be made into factory worlds or recruiting worlds; each providing its own benefits
//Factory worlds improve the quality of the players army allowing it to deal more damage and take less
//Cloning worlds produce new soldiers for the army, giving it more men

//Planets have a front size determined by the API call, this determines how much of the players army can deploy
//Planets have various factors which determine how well they work as a factory, clone, or logistic world

//Each game contains 20 planets randomly determined by the API
@Entity
@Table(name="planets")
@Getter
@Setter
@EqualsAndHashCode(exclude="galaxies")
@ToString(exclude="galaxies")
@NoArgsConstructor
@AllArgsConstructor
@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="id")
public class Planet {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@JsonView({ JsonViewProfiles.Galaxy.class, JsonViewProfiles.Planet.class })
	private int id;// planet id 
	
	private String url; // the planet's url
	private String image;
	/*
	 * these fields are used in the classification of a planet
	 */
	
	@Column(scale=2)
	private double gravity;//The gravity of a planet 
	private String climate;//The climate of a planet 
	@Column(unique=true, nullable=false)
	private String name; // Name of the planet(Unique)
	private String terrain; 
	/*
	 *  these fields are used in the ranking of planets
	 */
	private int tier;
	private int average;
	/*
	 *  these fields determine how effective a planet is at maintaining/improving the army
	 */
	private int recruitment;
	private int factory;
	//The diameter of a planet determines how well it serves as a factory
	private int diameter;
	//The population of a planet determines how many troops can be recruited from it
	private long population;
	
	// A list of galaxies that this planet is a part of
	@ManyToMany(mappedBy = "planets", fetch=FetchType.LAZY)
	@JsonView(JsonViewProfiles.Planet.class)
	@JsonIgnore
    private Set<Galaxy> galaxies;

	
	public long recruitingValue() {
		long population = this.population;
		long recruitment = 0;
		while (population > 10) {
			recruitment ++;
			population = population/10;
		}
		
		return recruitment;
	}
	
	public int factoryValue(Planet planet) {
		
		int diameter = this.diameter;
		int factory = 0;
		while (diameter > 10) {
			factory++;
			diameter = diameter/10;
		}
		
		return factory;
	}
	
	public Planet(String url, String image, double gravity, String climate, String name, String terrain, int tier,
			int average, int recruitment, int factory, int diameter, long population, Set<Galaxy> galaxies) {
		super();
		this.url = url;
		this.image = image;
		this.gravity = gravity;
		this.climate = climate;
		this.name = name;
		this.terrain = terrain;
		this.tier = tier;
		this.average = average;
		this.recruitment = recruitment;
		this.factory = factory;
		this.diameter = diameter;
		this.population = population;
		this.galaxies = galaxies;
	}
}

