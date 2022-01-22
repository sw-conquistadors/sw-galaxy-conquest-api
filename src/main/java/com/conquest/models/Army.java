package com.conquest.models;

import java.util.Objects;

//The players army is their primary means of interaction with the game
//The army moves around the map
//It attacks planets
//It has a size and equipment combine to give its power

public class Army {
	private int factory = 5;
	private int size = 5;
	private int power = (strength * size);
	
	//
	public int battle(Planet planet) {
		int planetPower = planet.getRecruit * planet.getFactory;
		int battle = -1;
		//Planets strength is higher then armies
		if (planetPower > power) {
			//This function returning a negative signals that the invasion cannot proceed
			return battle;
		} else {
			if ((power-planetPower) < 10) {
				size += power-planetPower-10;
				battle = power-planetPower-10;
			}
		
			size += planet.getRecruit/5;
			factory += planet.getFactory/5;	
			
			power = size*factory;
			
			return battle;	
		}
		
	}
	
	
	//Getters and Setters
	public int getStrength() {
		return strength;
	}
	public void setStrength(int strength) {
		this.strength = strength;
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	public int getPower() {
		return power;
	}
	public void setPower(int power) {
		this.power = power;
	}

	@Override
	public int hashCode() {
		return Objects.hash(power, size, strength);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Army other = (Army) obj;
		return power == other.power && size == other.size && strength == other.strength;
	}
	
}
