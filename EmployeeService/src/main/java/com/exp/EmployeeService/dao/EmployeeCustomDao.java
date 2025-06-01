package com.exp.EmployeeService.dao;

import com.exp.EmployeeService.dto.EmployeeDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@Component
public class EmployeeCustomDao {

    @Autowired
    private ReactiveMongoTemplate mongoTemplate;

    public Flux<EmployeeDto> getAllEmployeesWithAddresses() {
        Aggregation aggregation = Aggregation.newAggregation(
                Aggregation.project("name", "age")
                        .andExpression("toString(_id)").as("idStr"),
                Aggregation.lookup("address", "idStr", "empId", "address")
        );
        return mongoTemplate.aggregate(aggregation, "EMPLOYEE_INFO", EmployeeDto.class);
    }

    public Mono<EmployeeDto> getEmployeeWithAddressesById(String empId) {
        Aggregation aggregation = Aggregation.newAggregation(
                Aggregation.project("name", "age")
                        .andExpression("toString(_id)").as("idStr"),
                Aggregation.match(Criteria.where("_id").is(empId)),
                Aggregation.lookup("address", "idStr", "empId", "address")
        );

        return mongoTemplate.aggregate(aggregation, "EMPLOYEE_INFO", EmployeeDto.class)
                .next();
    }





}
