package com.exp.EmployeeService.utility;

import com.exp.EmployeeService.dao.EmployeeInfoDAO;
import com.exp.EmployeeService.entity.EmployeeInfo;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class CustomUtility {
    @Autowired
    private EmployeeInfoDAO employeeInfoDAO;
    @Tool(description = "This is used to fetch All employee list")
    public List<EmployeeInfo> fetchAllEmployeeInfo() {
        var employeeInfoList = employeeInfoDAO.findAll().collectList().block();
        return employeeInfoList;
    }

}
