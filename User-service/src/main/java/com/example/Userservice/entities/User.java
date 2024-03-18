package com.example.Userservice.entities;


import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter @Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private Long id;

    @NotBlank(message = "First name is null ")
    @Column(name = "first_name", nullable = false)
    private String firstName;

    @NotBlank(message = "Last name is null")
    @Column(name = "last_name")
    private String lastName;


    @Email(message = "Email not valid check your mail !!! ")
    @Column(name = "email", unique = true)
    private String email;

}
