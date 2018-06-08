package com.spring.secuirty.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.spring.security.dto.Employee;
import com.spring.security.repository.EmployeeRepository;

@Controller
public class LoginController {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private EmployeeRepository employeeRepository;
	
    @RequestMapping("/")
    public String home(Model model) {
    	String message = "Login was Successfully";
    	
        return message;
    }
    
    @RequestMapping("/employee")
    public ArrayList<Employee> getEmployeeListJSON() {
        ArrayList<Employee> employeeList = new ArrayList<>();
        List<Employee> employeeListFromDB = employeeRepository.findAll();
        logger.info("employeeListFromDB total list size----  :"+employeeListFromDB.size());
        for (Employee employee : employeeListFromDB) {
        	employeeList.add(employee);
        }

        return employeeList;
    }
    


    @RequestMapping("/add/{employeeName}")
    public ArrayList<Employee> addEmployeeToDB(@PathVariable String employeeName) {
    	employeeRepository.save(new Employee(employeeName));
        ArrayList<Employee> result = new ArrayList<>();
        List<Employee> employeeObjectListByName = employeeRepository.findByNameIgnoreCase(employeeName);
        logger.info("employee name size after add----  :"+employeeObjectListByName.size());
        for (Employee ans : employeeObjectListByName) {
            result.add(ans);
        }
        return result;
    }
    
    
    @RequestMapping("/get/{employeeName}")
    public ArrayList<Employee> getEmployeeFromDB(@PathVariable String employeeName) {
        ArrayList<Employee> result = new ArrayList<>();
        for (Employee ans : employeeRepository.findByNameIgnoreCase(employeeName)) {
        	logger.info("employee name from db----  :"+ans.getEmployeeName());
            result.add(ans);
        }
        return result;
    }


}