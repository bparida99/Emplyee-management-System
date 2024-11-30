package com.exp.EmployeeService.controller;

import com.exp.EmployeeService.entity.EmployeeInfo;
import com.exp.EmployeeService.service.EmployeeInfoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/v1")
public class EmployeeInfoController {

    @Autowired
    private EmployeeInfoService employeeInfoService;

    @PostMapping("/saveEmployeeInfo")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<EmployeeInfo> saveEmployeeInfo(@RequestBody @Valid EmployeeInfo employeeInfo){
        return employeeInfoService.addEmployeeInfo(employeeInfo).log();
    }

    @GetMapping("/fetchAllEmployees")
    @ResponseStatus(HttpStatus.FOUND)
    public Flux<EmployeeInfo> fetchAllEmployees(){
        return employeeInfoService.fetchAllEmployeeInfo().log();
    }

    @GetMapping("/findById/{id}")
    @ResponseStatus(HttpStatus.FOUND)
    public Mono<EmployeeInfo> findById(@PathVariable String id){
        return employeeInfoService.findEmployeeById(id).log();
    }

    @PutMapping("/updateEmployeeInfo/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Mono<EmployeeInfo> updateEmployeeInfo(@RequestBody EmployeeInfo employeeInfo,
                                                 @PathVariable String id){
        return employeeInfoService.updateEmpInfo(employeeInfo,id).log();
    }

}
