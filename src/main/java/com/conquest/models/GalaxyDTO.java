package com.conquest.models;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import lombok.Data;
import lombok.NoArgsConstructor;

public final class GalaxyDTO {
	
	private int id;
	private Set<PlanetDTO> planets = new HashSet<PlanetDTO>();
	
	public GalaxyDTO() {
		
	}
	
	public GalaxyDTO(Galaxy galaxy) {
		System.out.println("making galaxyDTO");
		this.id = galaxy.getId();
		System.out.println("setting galaxy ID");
		System.out.println(galaxy.getPlanets());
		for (Planet planet : galaxy.getPlanets()) {
			PlanetDTO planetDTO = new PlanetDTO();
			System.out.println("Making new PlanetDTO");
			planetDTO.setId(planet.getId());
			planetDTO.setUrl(planet.getUrl());
			planetDTO.setImage(planet.getImage());
			planetDTO.setGravity(planet.getGravity());
			planetDTO.setClimate(planet.getClimate());
			planetDTO.setName(planet.getName());
			planetDTO.setTerrain(planet.getTerrain());
			planetDTO.setTier(planet.getTier());
			planetDTO.setAverage(planet.getAverage());
			planetDTO.setRecruitment(planet.getRecruitment());
			planetDTO.setFactory(planet.getFactory());
			planetDTO.setDiameter(planet.getDiameter());
			planetDTO.setPopulation(planet.getPopulation());
			System.out.println("adding planetDTO");
			this.planets.add(planetDTO);
		}
	}

	@Override
	public String toString() {
		return "GalaxyDTO [id=" + id + ", planets=" + planets + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, planets);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		GalaxyDTO other = (GalaxyDTO) obj;
		return id == other.id && Objects.equals(planets, other.planets);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Set<PlanetDTO> getPlanets() {
		return planets;
	}

	public void setPlanets(Set<PlanetDTO> planets) {
		this.planets = planets;
	}
	
	
}
