package com.ai.aibotservice.service;

import com.ai.aibotservice.dto.EmployeeInfo;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.tool.ToolCallbackProvider;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OllamaChatBotService {
    private final ChatClient chatClient;

    private final ToolCallbackProvider toolCallbackProvider;

    OllamaChatBotService(ChatClient.Builder chatClientBuilder,ToolCallbackProvider toolCallbackProvider){
        this.chatClient = chatClientBuilder.build();
        this.toolCallbackProvider = toolCallbackProvider;
    }

    public Object generateResult(String prompt) {
        ChatClient.CallResponseSpec response =
                chatClient.prompt(prompt).tools(toolCallbackProvider).call();
        try {
            List<EmployeeInfo> employeeList = response.entity(List.class);
            if (!employeeList.isEmpty()) {
                return employeeList;
            }
        } catch (Exception ignored) {
        }
        return "No data found";
    }
}
