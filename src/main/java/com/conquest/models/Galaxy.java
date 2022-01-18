package com.conquest.models;

import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

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
	@Column(name="galaxy_id")
	private int id;
	//Creates a map which will be used to keep track of the planet objects within the Galaxy
	@ManyToMany
	private Set<Planet> locations;
}
