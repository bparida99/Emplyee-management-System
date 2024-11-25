package com.exp.EmployeeService.dao;

import com.exp.EmployeeService.entity.EmployeeInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeInfoDAO extends JpaRepository<EmployeeInfo,Long> {
}
