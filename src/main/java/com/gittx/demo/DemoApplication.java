package com.gittx.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);

	}

//	@Bean
//	public BCryptPasswordEncoder bCryptPasswordEncoder() {    return new BCryptPasswordEncoder(); }

}
