package Tests;

import static org.junit.Assert.*;



import org.junit.Test;

import ADT.List;
import IO.EmployeeIO;
import Modules.Employee;

public class EmployeeTestCases {

	@Test
	public void testForEmployeeLogin() {
		Employee employee = new Employee("admin","admin");
		String expected_username = "admin";
		String expected_password = "admin";
		
		assertEquals(expected_username,employee.getUsername());
		assertEquals(expected_password,employee.getPassword());
	}
	
	@Test
	public void testForEmptyEmployeeUsername() {
		
		Employee emp1 = new Employee("admin","admin");
		boolean expected = false;
		assertEquals(emp1.getUsername().isEmpty(),expected);
	}
	
	@Test
	public void testForEmptyEmployeePassword() {
		
		Employee emp1 = new Employee("admin","admin");
		boolean expected = false;
		assertEquals(emp1.getPassword().isEmpty(),expected);
	}
	
	@Test
	public void testForDuplicateEmployee() {
		
		Employee emp1 = new Employee("admin","admin");
		Employee emp2 = new Employee("admin","admin");
		int expected = 0;
		assertEquals(emp1.compareTo(emp2),expected);
	}
	
	@Test
	public void testForReadEmployee() {
		
		EmployeeIO emp1 = new EmployeeIO("input.txt");
		List<Employee> list = emp1.readfile();
		boolean expected = true;
		assertEquals(!list.isEmpty(),expected);
	}

}
