package com.mentor4you;

import com.mentor4you.repository.MessageRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@Tag(name = "Some Entity", description = "Test description")
@RestController
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Operation(summary = "Test method", description = "Returns a Hello World")

	public String hello(@RequestParam(value = "name", defaultValue = "Neo") String name) {
		return String.format("Wake up, %s.\n The Mentor4you has you...", name);
	}

	@Operation(summary = "Test security method")
	@GetMapping("/testSecurity")
	public String testSecurity() {
		return String.format("Security work");
	}



}
