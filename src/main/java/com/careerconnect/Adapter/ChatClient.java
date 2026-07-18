package com.careerconnect.Adapter;


public interface ChatClient {

    /**
     * Sends a prompt to the AI model and returns the generated response.
     *
     * @param prompt User prompt
     * @return AI generated response
     */
    String generateResponse(String prompt);

}