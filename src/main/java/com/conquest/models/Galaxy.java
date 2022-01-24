package com.conquest.models;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.conquest.services.JsonViewProfiles;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="galaxies")
@Getter
@Setter
@EqualsAndHashCode(exclude="planets")
@ToString(exclude="planets")
@NoArgsConstructor
@AllArgsConstructor
@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="id")
public final class Galaxy {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@JsonView({ JsonViewProfiles.Galaxy.class, JsonViewProfiles.Planet.class })
	private int id;
	//Creates a map which will be used to keep track of the planet objects within the Galaxy
	@ManyToMany(fetch=FetchType.LAZY)
	@JoinTable(
			name="galaxies_planets",
			joinColumns = @JoinColumn(name="galaxy_id"),
			inverseJoinColumns = @JoinColumn(name="planet_id"))
	@JsonView(JsonViewProfiles.Galaxy.class)
	private Set<Planet> planets;
	
	public Galaxy(Set<Planet> planets) {
		super();
		this.planets = planets;
	}
	
//	public Set<Planet> getPlanets() {
//		
//	}
//	
}
