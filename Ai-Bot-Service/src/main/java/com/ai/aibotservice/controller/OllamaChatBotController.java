package com.ai.aibotservice.controller;

import com.ai.aibotservice.service.OllamaChatBotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OllamaChatBotController {

    @Autowired
    private OllamaChatBotService ollamaChatBotService;

    @GetMapping("/chat/{prompt}")
    public Object chat(@PathVariable String prompt){
        return ollamaChatBotService.generateResult(prompt);
    }
}
