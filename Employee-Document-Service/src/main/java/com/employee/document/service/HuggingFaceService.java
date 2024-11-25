package com.employee.document.service;

import com.employee.document.config.HuggingFaceProperties;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class HuggingFaceService {

    @Autowired
    private HuggingFaceProperties huggingFaceProperties;

    private static final int MAX_RETRIES = 5;
    private static final long RETRY_DELAY_MS = 5000; // 5 seconds delay between retries

    public JsonNode getCompletion(String inputText) {
        RestTemplate restTemplate = new RestTemplate();

        // Set headers
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + huggingFaceProperties.getApiToken());
        headers.set("Content-Type", "application/json");

        // Create the request payload
        Map<String, Object> payload = Map.of(
                "inputs", inputText

        );

        // Create the HttpEntity with the payload and headers
        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(payload, headers);

        // Retry logic
        int retries = 0;
        boolean success = false;
        JsonNode responseBody = null;

        while (retries < MAX_RETRIES && !success) {
            try {
                ResponseEntity<JsonNode> response = restTemplate.exchange(
                        huggingFaceProperties.getApiUrl(), HttpMethod.POST, entity, JsonNode.class);

                if (response.getStatusCode() == HttpStatus.OK) {
                    // Successfully got a response
                    responseBody = response.getBody();
                    success = true;
                } else {
                    // If it's not OK, log and break
                    System.out.println("Error: " + response.getStatusCode());
                    break;
                }
            } catch (HttpServerErrorException e) {
                // Handle 503 Service Unavailable (model loading)
                if (e.getStatusCode() == HttpStatus.SERVICE_UNAVAILABLE) {
                    System.out.println("Model is loading, retrying in " + RETRY_DELAY_MS / 1000 + " seconds...");
                    retries++;
                    try {
                        Thread.sleep(RETRY_DELAY_MS); // Delay before retrying
                    } catch (InterruptedException ie) {
                        Thread.currentThread().interrupt();
                        break;
                    }
                } else {
                    // Handle other HTTP errors
                    System.out.println("Error: " + e.getMessage());
                    break;
                }
            } catch (Exception e) {
                // Handle other exceptions
                System.out.println("Error: " + e.getMessage());
                break;
            }
        }

        if (!success) {
            System.out.println("Failed to get a valid response after " + MAX_RETRIES + " retries.");
        }

        return responseBody;
    }
}
