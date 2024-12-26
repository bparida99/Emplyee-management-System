package com.employee.document.config;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@ConfigurationProperties(prefix = "spring.huggingface")
@Getter
@Setter
public class HuggingFaceProperties {

    private String apiUrl;
    private String apiToken;

}
