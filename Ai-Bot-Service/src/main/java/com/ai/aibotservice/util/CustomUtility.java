package com.ai.aibotservice.util;

import org.springframework.ai.tool.annotation.Tool;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class CustomUtility {

    @Tool(description = "This is used to fetch the current date and time")
    public String getDateAndTime() {
        return LocalDateTime.now()
        .atZone(LocaleContextHolder.getTimeZone().toZoneId())
        .toString();
    }
}
