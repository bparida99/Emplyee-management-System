package com.exp.EmployeeService.entity;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "EMPLOYEE_INFO")
public class EmployeeInfo {
    @Id
    private String id;
    private String name;
    private int age;
}
