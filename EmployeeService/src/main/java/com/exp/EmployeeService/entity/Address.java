package com.exp.EmployeeService.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "address")
public class Address {
    @Id
    private String id;
    private String houseNo;
    private String street;
    private String city;
    private String state;
    private String zipCode;
    private String country;
    private String type;
    private String empId;

}
