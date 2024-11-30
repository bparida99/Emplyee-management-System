package com.exp.EmployeeService.service;

import com.exp.EmployeeService.dao.EmployeeInfoDAO;
import com.exp.EmployeeService.entity.EmployeeInfo;
import org.apache.logging.log4j.LogBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class EmployeeInfoService {
    @Autowired
    private EmployeeInfoDAO employeeInfoDAO;

    public Mono<EmployeeInfo> addEmployeeInfo(EmployeeInfo employeeInfo) {
       return employeeInfoDAO.save(employeeInfo);
    }

    public Flux<EmployeeInfo> fetchAllEmployeeInfo() {
        return employeeInfoDAO.findAll();
    }

    public Mono<EmployeeInfo> findEmployeeById(String id) {
        return employeeInfoDAO.findById(id);
    }

    public Mono<EmployeeInfo> updateEmpInfo(EmployeeInfo employeeInfo,String id) {
        return employeeInfoDAO.findById(id).flatMap(info->{
           info.setAge(employeeInfo.getAge());
            return employeeInfoDAO.save(info);
        });
    }
}
