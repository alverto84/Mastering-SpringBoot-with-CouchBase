package com.example.couchbase65.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.couchbase65.dao.Employee;
import com.example.couchbase65.service.EmployeeService;

@RestController
@RequestMapping(path = "/employee")
public class EmployeeController {
	@Autowired
	private EmployeeService employeeService;

	@PostMapping(path = "/save")
	public void saveEmployees() {
		
		Employee e1 = new Employee();
		e1.setId("1");
		e1.setName("Mark");
		
		Employee e2 = new Employee();
		e2.setId("2");
		e2.setName("Taylor");
		
		employeeService.saveAll(e1, e2);
		
	}
	

}
