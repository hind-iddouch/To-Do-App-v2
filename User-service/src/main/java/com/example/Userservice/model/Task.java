package com.example.Userservice.model;

import lombok.*;

import java.time.LocalDate;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class Task {



        private Long id;
        private String title;
        private String description;
        private Long userId;
        private Status status;
        private LocalDate dueDate;
        private Date updatedAt;



}
