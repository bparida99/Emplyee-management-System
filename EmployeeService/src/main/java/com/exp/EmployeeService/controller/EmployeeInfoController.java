package com.exp.EmployeeService.controller;

import com.exp.EmployeeService.entity.EmployeeInfo;
import com.exp.EmployeeService.service.EmployeeInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/v1")
public class EmployeeInfoController {

    @Autowired
    private EmployeeInfoService employeeInfoService;

    @PostMapping("/saveEmployeeInfo")
    public Mono<EmployeeInfo> saveEmployeeInfo(@RequestBody EmployeeInfo employeeInfo){
        return employeeInfoService.addEmployeeInfo(employeeInfo).log();
    }
}
