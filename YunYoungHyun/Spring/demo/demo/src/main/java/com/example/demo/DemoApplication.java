package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		System.out.println("하이요~");
		System.out.println("Hello World!!");
		SpringApplication.run(DemoApplication.class, args);
	}

}