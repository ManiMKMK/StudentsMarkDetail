package com.student.StudentsMarkDetail;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.student.StudentsMarkDetail")
public class StudentsMarkDetailApplication {

	public static void main(String[] args) {
		SpringApplication.run(StudentsMarkDetailApplication.class, args);
	}

}
