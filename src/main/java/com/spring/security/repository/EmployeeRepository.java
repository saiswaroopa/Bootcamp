package com.spring.security.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.security.dto.Employee;


public interface EmployeeRepository extends JpaRepository<Employee, Long> {
	
	List<Employee> findByNameIgnoreCase(String employeeName);


}
