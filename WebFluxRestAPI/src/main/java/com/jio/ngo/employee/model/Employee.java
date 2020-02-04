package com.jio.ngo.employee.model;

import java.io.Serializable;

import lombok.Data;

@Data
public class Employee implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	private String idmFirstName;
	private String idmMiddleName;
	private String idmLastName;
	private String idmName;
	private String idmDomainId;
	private String idmRilEmail;
	

}
