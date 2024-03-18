package com.example.Userservice.entities.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class UserRequestDto {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
}
