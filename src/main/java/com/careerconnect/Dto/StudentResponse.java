package com.careerconnect.Dto;


import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class StudentResponse {

    private String studentId;

    private String name;

    private String email;

    private String programme;

    private int graduationYear;

    private double cgpa;

    private int activeBacklogs;

    private List<String> skills;
}