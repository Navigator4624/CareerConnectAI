package com.careerconnect.Dto;



import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class ChatRequest {

    private String studentId;

    private String driveId;

    private String message;
}
