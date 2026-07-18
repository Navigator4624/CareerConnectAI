package com.careerconnect.Dto;



import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class ChatResponse {

    private String answer;

    private String model;

    private boolean advisory;
}
