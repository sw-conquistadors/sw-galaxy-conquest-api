package com.conquest.models;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import lombok.Data;
import lombok.NoArgsConstructor;

//Planets are the players primary challenge in the game as well as the source of resources
//Planets may be made into factory worlds or recruiting worlds; each providing its own benefits
//Factory worlds improve the quality of the players army allowing it to deal more damage and take less
//Cloning worlds produce new soldiers for the army, giving it more men

//Planets have a front size determined by the API call, this determines how much of the players army can deploy
//Planets have various factors which determine how well they work as a factory, clone, or logistic world

//Each game contains 20 planets randomly determined by the API

public class PlanetDTO {
	
	private int id;// planet id 
	
	private String url; // the planet's url
	private String image;

	private double gravity;//The gravity of a planet 
	private String climate;//The climate of a planet 
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
	
	public PlanetDTO() {
	}
	
	public PlanetDTO(Planet planet) {
		System.out.println("making planetDTO thingermajober");
		this.id = planet.getId();
		this.url = planet.getUrl();
		this.image = planet.getImage();
		this.gravity = planet.getGravity();
		this.climate = planet.getClimate();
		this.name = planet.getName();
		this.terrain = planet.getTerrain();
		this.tier = planet.getTier();
		this.average = planet.getAverage();
		this.recruitment = planet.getRecruitment();
		this.factory = planet.getFactory();
		this.diameter = planet.getDiameter();
		this.population = planet.getPopulation();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public double getGravity() {
		return gravity;
	}

	public void setGravity(double gravity) {
		this.gravity = gravity;
	}

	public String getClimate() {
		return climate;
	}

	public void setClimate(String climate) {
		this.climate = climate;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTerrain() {
		return terrain;
	}

	public void setTerrain(String terrain) {
		this.terrain = terrain;
	}

	public int getTier() {
		return tier;
	}

	public void setTier(int tier) {
		this.tier = tier;
	}

	public int getAverage() {
		return average;
	}

	public void setAverage(int average) {
		this.average = average;
	}

	public int getRecruitment() {
		return recruitment;
	}

	public void setRecruitment(int recruitment) {
		this.recruitment = recruitment;
	}

	public int getFactory() {
		return factory;
	}

	public void setFactory(int factory) {
		this.factory = factory;
	}

	public int getDiameter() {
		return diameter;
	}

	public void setDiameter(int diameter) {
		this.diameter = diameter;
	}

	public long getPopulation() {
		return population;
	}

	public void setPopulation(long population) {
		this.population = population;
	}

	@Override
	public int hashCode() {
		return Objects.hash(average, climate, diameter, factory, gravity, id, image, name, population, recruitment,
				terrain, tier, url);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PlanetDTO other = (PlanetDTO) obj;
		return average == other.average && Objects.equals(climate, other.climate) && diameter == other.diameter
				&& factory == other.factory
				&& Double.doubleToLongBits(gravity) == Double.doubleToLongBits(other.gravity) && id == other.id
				&& Objects.equals(image, other.image) && Objects.equals(name, other.name)
				&& population == other.population && recruitment == other.recruitment
				&& Objects.equals(terrain, other.terrain) && tier == other.tier && Objects.equals(url, other.url);
	}

	@Override
	public String toString() {
		return "PlanetDTO [id=" + id + ", url=" + url + ", image=" + image + ", gravity=" + gravity + ", climate="
				+ climate + ", name=" + name + ", terrain=" + terrain + ", tier=" + tier + ", average=" + average
				+ ", recruitment=" + recruitment + ", factory=" + factory + ", diameter=" + diameter + ", population="
				+ population + "]";
	}


    

}

