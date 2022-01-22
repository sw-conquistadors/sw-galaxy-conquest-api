package com.conquest.services;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.stream.Collectors;

import javax.servlet.annotation.WebFilter;

import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.filter.OncePerRequestFilter;

//@Component
//@WebFilter("/*") // Our frontend Angular Application will be running on port 4200 and making request to our localhost at port 5000
//public class CorsFilterCustom extends OncePerRequestFilter {
//
//    @Override
//    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse resp, FilterChain chain)
//            throws ServletException, IOException {
//        resp.setHeader("Access-Control-Allow-Origin", "*");
//        resp.setHeader("Access-Control-Allow-Credentials", "true");
//        resp.setHeader("Access-Control-Allow-Methods", "GET,HEAD,OPTIONS,POST,PUT,DELETE,PATCH");
//        resp.setHeader("Access-Control-Allow-Headers", "Access-Control-Allow-Headers, Origin,Accept, X-Requested-With, Content-Type, Access-Control-Request-Method, Access-Control-Request-Headers");
//        chain.doFilter(req, resp);    
//    }
//
//}
@Component
@WebFilter("/*")
public class CorsFiltration {

	public CorsFilter corsFilter() {

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
