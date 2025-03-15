package com.ai.aibotservice.service;

import com.ai.aibotservice.util.CustomUtility;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OllamaChatBotService {
    private final CustomUtility customUtility;
    private final ChatClient chatClient;

    OllamaChatBotService(ChatClient.Builder chatClientBuilder,CustomUtility customUtility){
        this.chatClient = chatClientBuilder.build();
        this.customUtility = customUtility;
    }

    public String generateResult(String prompt){
        return  chatClient
                .prompt(prompt)
                .tools(customUtility)
                .call().content();
    }
}
