package com.example.Taskservice;

import com.example.Taskservice.entities.dto.TaskRequestDto;
import com.example.Taskservice.enums.Status;
import com.example.Taskservice.services.TaskService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


import java.time.LocalDate;



@SpringBootApplication
public class TaskServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(TaskServiceApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(TaskService taskService){
		return args ->{

			TaskRequestDto taskDto1 = new TaskRequestDto(null, "Task 1", "Description for Task 1", Long.valueOf(1), Status.IS_DONE, LocalDate.of(2025, 1, 1));
			TaskRequestDto taskDto2 = new TaskRequestDto(null, "Task 2", "Description for Task 2", Long.valueOf(2), Status.IN_PROGRESS, LocalDate.of(2025, 1, 1));
			TaskRequestDto taskDto3 = new TaskRequestDto(null, "Task 3", "Description for Task 3", Long.valueOf(3), Status.NOT_DONE, LocalDate.of(2025, 1, 1));

			taskService.createTask(taskDto1);
			taskService.createTask(taskDto2);
			taskService.createTask(taskDto3);


		};


	}


}
