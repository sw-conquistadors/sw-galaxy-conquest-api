package com.conquest.models;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.conquest.services.JsonViewProfiles;
import com.fasterxml.jackson.annotation.JsonView;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@EqualsAndHashCode(exclude="galaxy")
@ToString(exclude="galaxy")
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name="games")
public class Game {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	// game has a SINGLE galaxy
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="galaxy_fk")
	private Galaxy galaxy;
	// possibly some sort of scoring (User)
	
	public Game(Galaxy galaxy) {
		super();
		this.galaxy = galaxy;
	}
	
	
}
