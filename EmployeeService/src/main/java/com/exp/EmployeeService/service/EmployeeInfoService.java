package com.exp.EmployeeService.service;

import com.exp.EmployeeService.dao.EmployeeInfoDAO;
import com.exp.EmployeeService.entity.EmployeeInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class EmployeeInfoService {
    @Autowired
    private EmployeeInfoDAO employeeInfoDAO;

    public Mono<EmployeeInfo> addEmployeeInfo(EmployeeInfo employeeInfo) {
       return Mono.just(employeeInfoDAO.save(employeeInfo));
    }
}
