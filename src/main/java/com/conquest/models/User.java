package com.conquest.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="users")
public class User {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	@Column(unique=true, nullable=false)
	private String username;
	@Column(name="pwd", nullable=false)
	private String password;

	public User(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}
}
