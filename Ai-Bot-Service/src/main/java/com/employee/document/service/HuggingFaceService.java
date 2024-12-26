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
    private static final long RETRY_DELAY_MS = 5000; // 5 seconds

    public JsonNode getCompletion(String inputText) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = createHeaders();
        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(createPayload(inputText), headers);

        for (int retries = 0; retries < MAX_RETRIES; retries++) {
            try {
                ResponseEntity<JsonNode> response = restTemplate.exchange(
                        huggingFaceProperties.getApiUrl(), HttpMethod.POST, entity, JsonNode.class);

                if (response.getStatusCode() == HttpStatus.OK) {
                    return response.getBody(); // Return the successful response
                } else {
                    System.out.println("Error: " + response.getStatusCode());
                    break;
                }
            } catch (HttpServerErrorException e) {
                if (e.getStatusCode() == HttpStatus.SERVICE_UNAVAILABLE) {
                    System.out.println("Model is loading, retrying...");
                    sleep(RETRY_DELAY_MS);
                } else {
                    System.out.println("HTTP Error: " + e.getMessage());
                    break;
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
                break;
            }
        }

        System.out.println("Failed to get a valid response after " + MAX_RETRIES + " retries.");
        return null;
    }

    private HttpHeaders createHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + huggingFaceProperties.getApiToken());
        headers.set("Content-Type", "application/json");
        return headers;
    }

    private Map<String, Object> createPayload(String inputText) {
        return Map.of("inputs", inputText);
    }

    private void sleep(long delayMs) {
        try {
            Thread.sleep(delayMs);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
