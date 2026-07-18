package com.careerconnect.Dto;



import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class StudentRequest {

    @NotBlank(message = "Name is required")
    private String name;

    @Email(message = "Invalid Email")
    private String email;

    private String programme;

    private int graduationYear;

    private double cgpa;

    private int activeBacklogs;

    private List<String> skills;
}