package com.restapi.controller;

import com.restapi.model.Employee;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class EmployeeController {
    @RequestMapping("/")
    public List<Employee> getEmployees() {
        List<Employee> employeeList = new ArrayList<Employee>();
        employeeList.add(new Employee(1, "Paulo", "Kinjo", "paulokinjo@hotmail.com", "DevOps Engineer"));
        return employeeList;
    }
}
