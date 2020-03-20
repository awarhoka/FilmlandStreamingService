package com.filmland.streaming;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FilmlandStreamingServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(FilmlandStreamingServiceApplication.class, args);
		System.out.println("FilmlandStreamingServiceApplication is Running");
	}

}
