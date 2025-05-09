package com.taskmanagement.taskmanager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = {"com.taskmanagement.repository" , 
		"com.taskmanagement.controllers" , "com.taskmanagement.serviceimplementations" ,
		"com.taskmanagement.services"})
@EntityScan("com.taskmanagement.entity")
@EnableJpaRepositories(basePackages = {"com.taskmanagement.repository"})

public class TaskmanagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(TaskmanagerApplication.class, args);
	}

}
