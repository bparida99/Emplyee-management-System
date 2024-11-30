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
    @NotBlank(message = "name can't be empty")
    private String name;
    @NotNull(message = "Age can't be null")
    @Positive(message = "age should be a positive number")
    @Min(value = 18,message = "Age should be more than 18")
    @Max(value = 70,message = "Age shouldn't be more than 70")
    private int age;
}
