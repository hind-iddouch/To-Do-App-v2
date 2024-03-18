package com.example.Taskservice.services;


import com.example.Taskservice.entities.dto.TaskRequestDto;
import com.example.Taskservice.entities.dto.TaskResponseDto;
import jakarta.persistence.EntityNotFoundException;

import java.util.List;

public interface TaskService {

    List<TaskResponseDto> getAllTasks();
    TaskResponseDto createTask(TaskRequestDto taskDto)throws EntityNotFoundException;
    TaskResponseDto getTaskById(Long id) throws EntityNotFoundException;
    TaskResponseDto updateTask (TaskRequestDto taskDto);
   void deleteTask(Long id) ;

}
