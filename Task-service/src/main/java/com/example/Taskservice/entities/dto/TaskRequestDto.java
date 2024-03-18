package com.example.Taskservice.entities.dto;


import com.example.Taskservice.enums.Status;
import lombok.*;

import java.time.LocalDate;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder

public class TaskRequestDto {

    private Long id;
    private String title;
    private String description;
    private Long userId;
    private Status status;
    private LocalDate dueDate;


}