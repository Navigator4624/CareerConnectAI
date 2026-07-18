package com.careerconnect.Dto;



import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class ApplicationRequest {

    @NotBlank(message = "Student Id is required")
    private String studentId;
}
