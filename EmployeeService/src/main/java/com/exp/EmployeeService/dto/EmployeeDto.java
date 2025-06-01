package com.exp.EmployeeService.dto;

import java.util.List;

public record EmployeeDto(String id,
                          String name,
                          int age,
                          List<AddressDto> address) {
}
