package com.exp.EmployeeService.dto;

public record AddressDto(String id,
                         String houseNo,
                         String street,
                         String city,
                         String state,
                         String zipCode,
                         String country,
                         String type,
                         String empId) {
}
