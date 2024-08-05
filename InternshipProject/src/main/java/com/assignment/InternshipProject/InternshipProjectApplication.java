package com.assignment.InternshipProject;

import com.assignment.InternshipProject.model.User;
import com.assignment.InternshipProject.repository.UserRepository;
import com.github.javafaker.Faker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.security.SecureRandom;
import java.util.Random;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class })
public class InternshipProjectApplication {
	private static final Logger log = LoggerFactory.getLogger(InternshipProjectApplication.class);
	private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
	private static final Random RANDOM = new SecureRandom();
	public static void main(String[] args) {
		SpringApplication.run(InternshipProjectApplication.class, args);
	}
	@Bean
	public CommandLineRunner commandLineRunner(
			UserRepository userRepository
	){
		return args -> {
			String filePath = "D:/WorkSpace/Assignment1Sing-main/inputUsers.txt";

			for(int i = 0; i < 100000; i++){
				Faker faker = new Faker();
				User user = new User();
				int length = 5000; // Độ dài của chuỗi ngẫu nhiên
				String randomString = generateRandomString(length);
				try(BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
					writer.write(faker.name().username()+",");
					writer.write("12345678,");
					writer.write(randomString);
					writer.newLine(); // Thêm dòng mới sau khi ghi nội dung
				}
//				user.setFirstName(faker.name().firstName());
//				user.setLastName(faker.name().lastName());
//				user.setPassword(faker.internet().password(8, 25));
//				user.setDateOfBirth(faker.date().birthday().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
//				user.setCreated_at(LocalDateTime.now());
//				user.setLast_edited(LocalDateTime.now());
				String userName = faker.name().username();

				do{
					userName = faker.name().username();
				} while(userRepository.findUserByUserName(userName).isPresent());
//				user.setUserName(userName);
//				userRepository.save(user);
			}
		};

	}
	public static String generateRandomString(int length) {
		StringBuilder stringBuilder = new StringBuilder(length);
		for (int i = 0; i < length; i++) {
			int randomIndex = RANDOM.nextInt(CHARACTERS.length());
			stringBuilder.append(CHARACTERS.charAt(randomIndex));
		}
		return stringBuilder.toString();
	}

}
