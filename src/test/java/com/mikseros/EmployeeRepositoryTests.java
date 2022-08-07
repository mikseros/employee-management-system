package com.mikseros;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import com.mikseros.model.Employee;
import com.mikseros.repository.EmployeeRepository;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class EmployeeRepositoryTests {

	@Autowired
	private EmployeeRepository repo;
	
	@Autowired
	private TestEntityManager entityManager;
	
	@Test
	public void testCreateNewEmployee() {
		Employee employee = new Employee("Jane", "Doe", "jane@doe.com");
		repo.save(employee);
	}
	
	@Test
	public void testGetEmployee() {
		Employee employee = repo.findById((long) 1).get();
//		entityManager.detach(employee);
		System.out.println(employee.getFirstName());
		System.out.println(employee.getLastName());
		System.out.println(employee.getEmailId());
	}
	
	@Test
	public void testGetAllEmployees() {
		List<Employee> employees = repo.findAll();
		assertThat(employees.size() > 0);
		for (Employee employee : employees) {
			System.out.println(employee.getFirstName());
			System.out.println(employee.getLastName());
			System.out.println(employee.getEmailId());
		}
	}
}
