package com.careerconnect.Dto;



import com.careerconnect.Enums.ApplicationStatus;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class ApplicationResponse {

    private String applicationId;

    private String studentId;

    private String driveId;

    private LocalDateTime submittedAt;

    private ApplicationStatus applicationStatus;
}
