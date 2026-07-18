package com.careerconnect.Dto;


import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class PlacementDriveRequest {

    private String companyId;

    private String role;

    private String location;

    private double packageOffered;

    private LocalDate deadline;

    private List<String> requiredSkills;

    private double minimumCgpa;

    private int maximumBacklogs;

    private String eligibleProgramme;

    private int graduationYear;
}