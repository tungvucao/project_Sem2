package com.qlBanGiay;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetails;

import com.qlBanGiay.entities.User;
import com.qlBanGiay.service.StorageService;
import com.qlBanGiay.service.UserService;

@SpringBootApplication
public class ProjectSem2Application {

	public static void main(String[] args) {
		SpringApplication.run(ProjectSem2Application.class, args);
	}
	
	@Bean
	CommandLineRunner init(StorageService storageService) {
		return (args) -> {
			storageService.init();
		};
	}
}
