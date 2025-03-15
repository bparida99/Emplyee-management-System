package com.ai.aibotservice.service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.tool.ToolCallbackProvider;
import org.springframework.stereotype.Service;

@Service
public class OllamaChatBotService {
    private final ChatClient chatClient;

    private final ToolCallbackProvider toolCallbackProvider;

    OllamaChatBotService(ChatClient.Builder chatClientBuilder,ToolCallbackProvider toolCallbackProvider){
        this.chatClient = chatClientBuilder.build();
        this.toolCallbackProvider = toolCallbackProvider;
    }

    public String generateResult(String prompt){
        return  chatClient
                .prompt(prompt)
                .tools(toolCallbackProvider)
                .call().content();
    }
}
