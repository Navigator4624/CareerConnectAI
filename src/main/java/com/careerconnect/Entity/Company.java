package com.careerconnect.Entity;



import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Company {

    private String companyId;

    @NotBlank(message = "Company name is required")
    private String companyName;

    private String sector;

    private String description;
}
