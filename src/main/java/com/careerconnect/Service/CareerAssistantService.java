package com.careerconnect.Service;



import com.careerconnect.Dto.ChatRequest;
import com.careerconnect.Dto.ChatResponse;
import com.careerconnect.Entity.PlacementDrive;
import com.careerconnect.Entity.Student;
import com.careerconnect.Repository.PlacementDriveRepository;
import com.careerconnect.Repository.StudentRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class CareerAssistantService {

    private final StudentRepository studentRepository;
    private final PlacementDriveRepository placementDriveRepository;
    private final RestTemplate restTemplate;

    private static final String OLLAMA_URL =
            "http://localhost:11434/api/generate";

    private static final String MODEL = "llama3";

    public CareerAssistantService(StudentRepository studentRepository,
                                  PlacementDriveRepository placementDriveRepository,
                                  RestTemplate restTemplate) {

        this.studentRepository = studentRepository;
        this.placementDriveRepository = placementDriveRepository;
        this.restTemplate = restTemplate;
    }

    public ChatResponse askAssistant(ChatRequest request) {

        Student student = studentRepository.findById(request.getStudentId())
                .orElseThrow(() ->
                        new RuntimeException("Student not found."));

        PlacementDrive drive = placementDriveRepository
                .findById(request.getDriveId())
                .orElseThrow(() ->
                        new RuntimeException("Placement Drive not found."));

        StringBuilder prompt = new StringBuilder();

        prompt.append("You are an AI Career Assistant.\n\n");

        prompt.append("Student Details:\n");
        prompt.append("Name: ").append(student.getName()).append("\n");
        prompt.append("Programme: ").append(student.getProgramme()).append("\n");
        prompt.append("CGPA: ").append(student.getCgpa()).append("\n");
        prompt.append("Backlogs: ").append(student.getActiveBacklogs()).append("\n");
        prompt.append("Skills: ").append(student.getSkills()).append("\n\n");

        prompt.append("Placement Drive Details:\n");
        prompt.append("Role: ").append(drive.getRole()).append("\n");
        prompt.append("Location: ").append(drive.getLocation()).append("\n");
        prompt.append("Required Skills: ")
                .append(drive.getRequiredSkills()).append("\n");
        prompt.append("Minimum CGPA: ")
                .append(drive.getMinimumCgpa()).append("\n\n");

        prompt.append("Question:\n");
        prompt.append(request.getMessage());

        Map<String, Object> body = new HashMap<>();

        body.put("model", MODEL);
        body.put("prompt", prompt.toString());
        body.put("stream", false);

        Map response = restTemplate.postForObject(
                OLLAMA_URL,
                body,
                Map.class
        );

        ChatResponse chatResponse = new ChatResponse();

        if (response != null && response.get("response") != null) {

            chatResponse.setAnswer(response.get("response").toString());

        } else {

            chatResponse.setAnswer("Unable to generate response.");
        }

        chatResponse.setModel(MODEL);
        chatResponse.setAdvisory(true);

        return chatResponse;
    }
}