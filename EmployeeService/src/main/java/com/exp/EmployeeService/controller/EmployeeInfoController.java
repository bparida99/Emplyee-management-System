package com.exp.EmployeeService.controller;

import com.exp.EmployeeService.entity.EmployeeInfo;
import com.exp.EmployeeService.service.EmployeeInfoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Sinks;

@RestController
@RequestMapping("/v1/employee")
public class EmployeeInfoController {

    @Autowired
    private EmployeeInfoService employeeInfoService;

    Sinks.Many<EmployeeInfo> employeeSync = Sinks.many().replay().all();

    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<EmployeeInfo> saveEmployeeInfo(@RequestBody @Valid EmployeeInfo employeeInfo) {
        return employeeInfoService.addEmployeeInfo(employeeInfo).log();
    }

    @GetMapping("/fetchAll")
    @ResponseStatus(HttpStatus.FOUND)
    public Flux<EmployeeInfo> fetchAllEmployees() {
        return employeeInfoService.fetchAllEmployeeInfo().log();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.FOUND)
    public Mono<EmployeeInfo> findById(@PathVariable String id) {
        return employeeInfoService.findEmployeeById(id).log();
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Mono<ResponseEntity<EmployeeInfo>> updateEmployeeInfo(@RequestBody EmployeeInfo employeeInfo,
                                                                 @PathVariable String id) {
        return employeeInfoService.updateEmpInfo(employeeInfo, id).map(employee -> {
                    return ResponseEntity.accepted().body(employee);
                })
                //.switchIfEmpty(Mono.just(ResponseEntity.notFound().build()))
                .doOnNext(employee -> employeeSync.tryEmitNext(employee.getBody()))
                .log();

    }

}
