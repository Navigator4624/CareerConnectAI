package com.careerconnect.Controller;


import com.careerconnect.Dto.ChatRequest;
import com.careerconnect.Dto.ChatResponse;
import com.careerconnect.Service.CareerAssistantService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/chat")
public class ChatController {

    private final CareerAssistantService careerAssistantService;

    public ChatController(CareerAssistantService careerAssistantService) {
        this.careerAssistantService = careerAssistantService;
    }

    // Ask AI Career Assistant
    @PostMapping
    public ResponseEntity<ChatResponse> askAssistant(
            @Valid @RequestBody ChatRequest request) {

        ChatResponse response =
                careerAssistantService.askAssistant(request);

        return ResponseEntity.ok(response);
    }
}