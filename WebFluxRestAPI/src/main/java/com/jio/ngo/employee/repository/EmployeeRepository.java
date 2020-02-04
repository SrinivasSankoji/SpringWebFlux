package com.jio.ngo.employee.repository;

import org.davidmoten.rx.jdbc.ConnectionProvider;
import org.davidmoten.rx.jdbc.Database;
import org.davidmoten.rx.jdbc.pool.NonBlockingConnectionPool;
import org.davidmoten.rx.jdbc.pool.Pools;
import org.springframework.stereotype.Component;

import com.jio.ngo.employee.model.Employee;

import io.reactivex.Flowable;
import reactor.core.publisher.Flux;

@Component
public class EmployeeRepository 
{
	private Database database;
	
	public EmployeeRepository()
	{
		try {
			ConnectionProvider connectionprovider = ConnectionProvider
			.from("jdbc:oracle:thin:@localhost:1521:XE", "system","bhaumik@23");
			NonBlockingConnectionPool pool =
	        Pools.nonBlocking()
            .maxPoolSize(Runtime.getRuntime().availableProcessors() * 5)
            .connectionProvider(connectionprovider)
            .build();
			 this.database = Database.from(pool);
		} catch (Exception e) {

		}
	}
	
	public Flux<Employee> getAllEmployees()
	{
		String sql="select IDM_FIRSTNAME,IDM_MIDDLENAME,IDM_LASTNAME,IDM_NAME,IDM_DOMAINID,IDM_RILEMAIL from TBLIDMASTER";
		Flowable<Employee> employeeFlowable= database.select(sql).get(resultset -> {
			Employee employee=new Employee();
			employee.setIdmFirstName(resultset.getString(1));
			employee.setIdmMiddleName(resultset.getString(2));
			employee.setIdmLastName(resultset.getString(3));
			employee.setIdmName(resultset.getString(4));
			employee.setIdmDomainId(resultset.getString(5));
			employee.setIdmRilEmail(resultset.getString(6));
			return employee;
		});
		return Flux.from(employeeFlowable);
	}

}
