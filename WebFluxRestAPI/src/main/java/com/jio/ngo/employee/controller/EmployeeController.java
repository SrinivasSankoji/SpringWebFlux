package com.jio.ngo.employee.controller;

import java.time.Duration;
import java.time.LocalTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jio.ngo.employee.model.Employee;
import com.jio.ngo.employee.repository.EmployeeRepository;

import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/employee")
public class EmployeeController 
{
	
	@Autowired
	EmployeeRepository employeeRepository;
	
	@GetMapping(value = "/dummyStream",produces = MediaType.TEXT_EVENT_STREAM_VALUE)
	public Flux<String> dummyStream()
	{
		return Flux.interval(Duration.ofSeconds(5)).log()
				.map(sequence -> "Flux -" + LocalTime.now().toString());

	}
	
	@GetMapping(value = "/employeeStream",produces = MediaType.TEXT_EVENT_STREAM_VALUE)
	public Flux<Employee> employeeStream()
	{
		return employeeRepository.getAllEmployees();

	}

}
