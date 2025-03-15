package com.ai.aibotservice.service;

import com.ai.aibotservice.util.CustomUtility;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class OllamaChatBotService {
    private final CustomUtility customUtility;
    private final ChatClient chatClient;

    OllamaChatBotService(ChatClient.Builder chatClientBuilder,CustomUtility customUtility){
        this.chatClient = chatClientBuilder.build();
        this.customUtility = customUtility;
    }

    public Mono<String> generateResult(String prompt){
        return  Mono.fromCallable(() ->chatClient
                .prompt(prompt)
                .tools(customUtility)
                .call().content());
    }
}
