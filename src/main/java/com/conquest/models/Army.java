package com.conquest.models;

import lombok.Data;

//The players army is their primary means of interaction with the game
//The army moves around the map
//It attacks planets
//It has a size and equipment combine to give its power

@Data
public class Army {
	private int factory = 5;
	private int size = 5;
	private int power = (factory * size);
	
	//
	public int battle(Planet planet) {
		int planetPower = planet.getRecruitment() * planet.getFactory();
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
		
			size += planet.getRecruitment()/5;
			factory += planet.getFactory()/5;	
			
			power = size*factory;
			
			return battle;	
		}
		
	}
	
}
