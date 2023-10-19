package com.basic.myspringboottest;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
@SpringBootApplication
public class MySpringBootTestApplication {

	public static void main(String[] args) {
		SpringApplication application = new SpringApplication(MySpringBootTestApplication.class);
		//WebApplication type 변경
		application.setWebApplicationType(WebApplicationType.SERVLET);
		application.run(args);
	}
	@Bean
	public ModelMapper modelMapper(){
		ModelMapper modelMapper = new ModelMapper();
		return modelMapper;
	}
}
