package com.conquest.models;

//Planets are the players primary challenge in the game as well as the source of resources
//Planets may be made into factory worlds or recruiting worlds; each providing its own benefits
//Factory worlds improve the quality of the players army allowing it to deal more damage and take less
//Cloning worlds produce new soldiers for the army, giving it more men

//Planets have a front size determined by the API call, this determines how much of the players army can deploy
//Planets have various factors which determine how well they work as a factory, clone, or logistic world

//Each game contains 20 planets randomly determined by the API
public class Planet {
	//The diameter of a planet determines how well it serves as a factory
	private int diameter;
	
	//The population of a planet determines how many troops can be recruited from it
	private int population;
	
	//The scope of a planet determines how many 
	private int scope;
}

