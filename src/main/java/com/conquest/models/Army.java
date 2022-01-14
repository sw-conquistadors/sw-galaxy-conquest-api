package com.conquest.models;

//The players army is their primary means of interaction with the game
//The army moves around the map
//It attacks planets
//It has a size and equipment combine to give its power

public class Army {
	int strength;
	int size;
	
	int power = strength*size;

}
