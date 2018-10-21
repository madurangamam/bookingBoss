package com.helix.leisure;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class BookingBossAppRunner {
	public static void main(String[] args) {
		SpringApplication.run(BookingBossAppRunner.class, args);
	}
}
