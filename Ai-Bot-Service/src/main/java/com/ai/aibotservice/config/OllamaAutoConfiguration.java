package com.ai.aibotservice.config;

import org.springframework.ai.autoconfigure.ollama.OllamaChatProperties;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class OllamaAutoConfiguration {
    @Value("${spring.ai.ollama.model}")
    private String model;

    @Bean
    @Primary
    public OllamaChatProperties chatClient(OllamaChatProperties ollamaProperties) {
        ollamaProperties.setModel(model);
        return ollamaProperties;
    }
}