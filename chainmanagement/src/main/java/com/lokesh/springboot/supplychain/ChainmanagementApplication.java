package com.lokesh.springboot.supplychain;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.lokesh.springboot.supplychain")
public class ChainmanagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(ChainmanagementApplication.class, args);
	}

}
