package com.careerconnect.Client;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Component
public class OllamaChatClient implements ChatClient {

    private final RestTemplate restTemplate;

    @Value("${ollama.base-url}")
    private String baseUrl;

    @Value("${ollama.model}")
    private String model;

    public OllamaChatClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public String generateResponse(String prompt) {

        String url = baseUrl + "/api/generate";

        Map<String, Object> requestBody = new HashMap<>();

        requestBody.put("model", model);
        requestBody.put("prompt", prompt);
        requestBody.put("stream", false);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Map<String, Object>> request =
                new HttpEntity<>(requestBody, headers);

        ResponseEntity<Map> response = restTemplate.exchange(
                url,
                HttpMethod.POST,
                request,
                Map.class
        );

        if (response.getBody() != null
                && response.getBody().get("response") != null) {

            return response.getBody()
                    .get("response")
                    .toString();
        }

        return "Unable to generate response from AI.";
    }
}
