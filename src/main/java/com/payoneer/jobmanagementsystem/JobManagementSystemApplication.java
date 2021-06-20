package com.payoneer.jobmanagementsystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class JobManagementSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(JobManagementSystemApplication.class, args);
	}

}
