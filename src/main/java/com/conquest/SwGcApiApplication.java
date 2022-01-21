package com.conquest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.conquest.services.PlanetService;

@SpringBootApplication
public class SwGcApiApplication implements CommandLineRunner{
	
	// logger
	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	PlanetService planetServ;
	
	
	public static void main(String[] args) {
		SpringApplication.run(SwGcApiApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		//planetServ.addAllPlanetsToDB();
		System.out.println("test");
	}
}
