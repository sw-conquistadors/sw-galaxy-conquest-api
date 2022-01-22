package com.conquest.services;

import java.util.Arrays;
import java.util.stream.Collectors;

import javax.servlet.annotation.WebFilter;

import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Component
@WebFilter("/*")
public class CorsFiltering {

	public CorsFilter doFilter() {

		final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		final CorsConfiguration config = new CorsConfiguration();
		config.setAllowCredentials(true); //
		config.setAllowedOrigins(Arrays.asList("http://localhost:4200"));
		config.addAllowedOriginPattern("*");
		config.setAllowedHeaders(Arrays.asList("*"));
		config.setAllowedMethods(Arrays.stream(HttpMethod.values()).map(HttpMethod::name).collect(Collectors.toList()));
		source.registerCorsConfiguration("/**", config);
		return new CorsFilter(source);
	}
}
