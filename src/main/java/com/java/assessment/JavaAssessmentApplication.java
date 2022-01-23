package com.java.assessment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class JavaAssessmentApplication {

	public static void main(String[] args) {
		SpringApplication.run(JavaAssessmentApplication.class, args);
	}

}
