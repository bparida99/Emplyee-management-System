package com.employee.document.service;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class LlamaService {

    private final RestTemplate restTemplate;


    public LlamaService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String processWithLlama(String inputText) {
        String apiUrl = "http://localhost:6000/";

        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");

        String requestBody = String.format("{\"text\": \"%s\"}", inputText);
        HttpEntity<String> request = new HttpEntity<>(requestBody, headers);

        ResponseEntity<String> response = restTemplate.exchange(apiUrl, HttpMethod.POST, request, String.class);
        return response.getBody();
    }
}
