package com.careerconnect.Dto;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class CompanyResponse {

    private String companyId;

    private String companyName;

    private String sector;

    private String description;
}
