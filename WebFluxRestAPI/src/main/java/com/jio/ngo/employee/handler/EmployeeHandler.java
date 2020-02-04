package com.jio.ngo.employee.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.jio.ngo.employee.model.Employee;
import com.jio.ngo.employee.repository.EmployeeRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class EmployeeHandler 
{
	@Autowired
	EmployeeRepository employeeRepository;
	
	public Mono<ServerResponse> getAllEmployees(ServerRequest serverRequest)
	{
		Flux<Employee> employees=employeeRepository.getAllEmployees();
		return ServerResponse.ok()
		.contentType(MediaType.APPLICATION_JSON)
		.body(employees, Employee.class);
				
	}
	

}
