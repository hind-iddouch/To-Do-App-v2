package com.example.Taskservice.utils;



import com.example.Taskservice.entities.Task;
import com.example.Taskservice.entities.dto.TaskRequestDto;
import com.example.Taskservice.entities.dto.TaskResponseDto;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

@NoArgsConstructor
public class Mapping {
    private static final ModelMapper modelMapper = new ModelMapper();

    public static Task maptoTask(TaskRequestDto taskRequestDto) {
        return modelMapper.map(taskRequestDto, Task.class);
    }

    public static TaskResponseDto mapToTaskResponseDto(Task task) {
        return modelMapper.map(task, TaskResponseDto.class);
    }




}

