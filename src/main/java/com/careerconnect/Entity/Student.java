package com.careerconnect.Entity;



import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Student {

    private String studentId;

    @NotBlank(message = "Student name is required")
    private String name;

    @Email(message = "Invalid email")
    private String email;

    private String programme;

    private int graduationYear;

    private double cgpa;

    private int activeBacklogs;

    private List<String> skills;
}
