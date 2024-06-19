package com.assignment.InternshipProject;

import com.assignment.InternshipProject.model.User;
import com.assignment.InternshipProject.repository.UserRepository;
import com.github.javafaker.Faker;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;
import java.time.ZoneId;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class })
public class InternshipProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(InternshipProjectApplication.class, args);
	}
//	@Bean
	public CommandLineRunner commandLineRunner(
			UserRepository userRepository
	){
		return args -> {
			for(int i = 0; i < 100; i++){
				Faker faker = new Faker();
				User user = new User();
				user.setFirstName(faker.name().firstName());
				user.setLastName(faker.name().lastName());
				user.setPassword(faker.internet().password(8, 25));
				user.setDateOfBirth(faker.date().birthday().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
				user.setCreated_at(LocalDateTime.now());
				user.setLast_edited(LocalDateTime.now());
				String userName = faker.name().username();
				do{
					userName = faker.name().username();
				} while(userRepository.findUserByUserName(userName).isPresent());
				user.setUserName(userName);
				userRepository.save(user);
			}
		};

	}

}
