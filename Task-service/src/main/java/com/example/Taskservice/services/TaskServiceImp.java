package com.example.Taskservice.services;


import com.example.Taskservice.entities.Task;
import com.example.Taskservice.entities.dto.TaskRequestDto;
import com.example.Taskservice.entities.dto.TaskResponseDto;
import com.example.Taskservice.repositories.TaskRepository;
import com.example.Taskservice.utils.Mapping;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.Taskservice.utils.Mapping.mapToTaskResponseDto;

@AllArgsConstructor
@Service
public class TaskServiceImp implements TaskService{
    private final TaskRepository taskRepository;
    @Override
    public List<TaskResponseDto> getAllTasks() {
        return taskRepository
                .findAll()
                .stream()
                .map(Mapping::mapToTaskResponseDto).toList();
    }
    @Override
    public TaskResponseDto getTaskById(Long id) throws EntityNotFoundException {
        Task task = taskRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("Task Not Found with the ID : "+id));

        return  mapToTaskResponseDto(task);
    }

    @Override
    public TaskResponseDto createTask(TaskRequestDto taskDto) throws  EntityNotFoundException{
        Task task = Mapping.maptoTask(taskDto);

        return mapToTaskResponseDto(taskRepository.save(task));
    }

    @Override
    public TaskResponseDto updateTask(TaskRequestDto taskDto){
        Task task = Mapping.maptoTask(taskDto);
        return mapToTaskResponseDto(taskRepository.save(task));
    }

   @Override
    public void deleteTask(Long id)  {
        taskRepository.deleteById(id);
    }



}
