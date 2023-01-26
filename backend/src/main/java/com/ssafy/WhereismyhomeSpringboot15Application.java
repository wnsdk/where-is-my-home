package com.ssafy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages= {"com.ssafy"})
public class WhereismyhomeSpringboot15Application {

	public static void main(String[] args) {
		SpringApplication.run(WhereismyhomeSpringboot15Application.class, args);
	}

}
