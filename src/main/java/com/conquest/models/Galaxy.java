package com.conquest.models;

import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.conquest.services.JsonViewProfiles;
import com.fasterxml.jackson.annotation.JsonView;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name="galaxies")
@NoArgsConstructor
@AllArgsConstructor
//The Galaxy 
public final class Galaxy {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@JsonView({ JsonViewProfiles.Galaxy.class, JsonViewProfiles.Planet.class })
	private int id;
	//Creates a map which will be used to keep track of the planet objects within the Galaxy
	@ManyToMany
	@JoinTable(name="galaxies_planets",
	joinColumns = @JoinColumn(name="galaxy_id"),
	inverseJoinColumns = @JoinColumn(name="planet_id"))
	@JsonView(JsonViewProfiles.Galaxy.class)
	private Set<Planet> planets;
	
	public Galaxy(Set<Planet> planets) {
		super();
		this.planets = planets;
	}
	
	
}
