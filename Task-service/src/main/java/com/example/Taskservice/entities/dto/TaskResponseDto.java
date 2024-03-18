package com.example.Taskservice.entities.dto;


import com.example.Taskservice.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter

public class TaskResponseDto {
    private Long id;
    private String title;
    private String description;
    private Long userId;
    private Status status;
    private LocalDate dueDate;
    private Date updatedAt;

}