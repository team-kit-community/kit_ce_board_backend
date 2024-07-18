package com.creativedesignproject.kumoh_board_backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableAspectJAutoProxy // aop 활성화
@SpringBootApplication
@EnableJpaAuditing
public class KumohBoardBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(KumohBoardBackendApplication.class, args);
	}
}