package com.exp.EmployeeService.config;
import com.exp.EmployeeService.utility.CustomUtility;
import org.springframework.ai.tool.ToolCallbackProvider;
import org.springframework.ai.tool.method.MethodToolCallbackProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CustomConfiguration {

    @Autowired
    private CustomUtility customUtility;

    @Bean
    public ToolCallbackProvider customTools(){
     return MethodToolCallbackProvider.builder().toolObjects(customUtility).build();
    }
}
