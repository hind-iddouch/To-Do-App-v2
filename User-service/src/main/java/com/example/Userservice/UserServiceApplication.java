package com.example.Userservice;



import com.example.Userservice.entities.dto.UserRequestDto;
import com.example.Userservice.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;



@SpringBootApplication
public class UserServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserServiceApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(UserService userService){
		return args ->{


			UserRequestDto userDto1 = new UserRequestDto(null, "John", "Doe", "john.doe@example.com");
			UserRequestDto userDto2 = new UserRequestDto(null, "Hind", "idd", "hind.idd@example.com");
			UserRequestDto userDto3 = new UserRequestDto(null, "Deniz", "can", "deniz.can@example.com");
			// Save users using UserService
			userService.createUser(userDto1);
			userService.createUser(userDto2);
			userService.createUser(userDto3);


		};


	}

}
