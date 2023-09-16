package com.springtodowebapp.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringTodoWebappApplication implements CommandLineRunner {

	@Autowired
	private CustomProperties props;
	
	public static void main(String[] args) {
		SpringApplication.run(SpringTodoWebappApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println(props.getApiUrl());		
	}

}
