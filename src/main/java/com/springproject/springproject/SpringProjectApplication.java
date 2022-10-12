package com.springproject.springproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication(scanBasePackages={"com.example.demo", "com.example.Controller"}, exclude = { SecurityAutoConfiguration.class })
//@EnableAutoConfiguration

//@ComponentScan())
@EnableJpaRepositories("com.springproject.springproject.repository")
public class SpringProjectApplication extends SpringBootServletInitializer {

	@Bean //when  described ad @bean it can be called from any spring component
	public PasswordEncoder passwordEncoder (){
		//This will create encrypted, secure,... password.
		//This will internally generate a random salt, so you will handle different result for each call.
		return new BCryptPasswordEncoder();
	}
	public static void main(String[] args) {
		SpringApplication.run(SpringProjectApplication.class, args);
	}

}