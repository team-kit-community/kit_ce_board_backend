package com.creativedesignproject.kumoh_board_backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@EnableAspectJAutoProxy
@SpringBootApplication
public class KumohBoardBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(KumohBoardBackendApplication.class, args);
	}
}