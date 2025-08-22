package com.muneesh;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.muneesh.DAO") // Enable JPA repositories in the specified package
@EntityScan(basePackages = "com.muneesh.Entity") // Scan for JPA entities in the specified package

@EnableMethodSecurity // Enable method security for the application
public class HospitalManagmentApplication {

	public static void main(String[] args) {
		SpringApplication.run(HospitalManagmentApplication.class, args);
		System.out.println(" SpringBoot Application for hospital management");
	}

}
