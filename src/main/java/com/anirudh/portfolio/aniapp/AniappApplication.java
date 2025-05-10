package com.anirudh.portfolio.aniapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan("com.anirudh.portfolio.aniapp.model")
public class AniappApplication {
	public static void main(String[] args) {
		SpringApplication.run(AniappApplication.class, args);
	}

}
