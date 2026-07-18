package com.careerconnect.Dto;



import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class CompanyRequest {

    @NotBlank(message = "Company name is required")
    private String companyName;

    private String sector;

    private String description;
}
