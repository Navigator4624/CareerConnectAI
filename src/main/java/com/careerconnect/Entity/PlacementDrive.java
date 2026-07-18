package com.careerconnect.Entity;


import com.careerconnect.Enums.DriveStatus;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class PlacementDrive {

    private String driveId;

    private String companyId;

    private String role;

    private String location;

    private double packageOffered;

    private LocalDate createdDate;

    private LocalDate deadline;

    private List<String> requiredSkills;

    private double minimumCgpa;

    private int maximumBacklogs;

    private String eligibleProgramme;

    private int graduationYear;

    private DriveStatus driveStatus;
}
