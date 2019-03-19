package Tests;

import static org.junit.Assert.*;
import Modules.Customer;
import Modules.Employee;
import Modules.Order;

import org.junit.Test;


public class CustomerTestCases {

	@Test
	public void testForCustomerFirstName() {
		Customer customer = new Customer();
		customer.setFirstName("tom");
		assertEquals(customer.getFirstName(),"tom");
		
	}
	
	@Test
	public void testForCustomerLastName() {
		Customer customer = new Customer();
		customer.setLastName("Rider");
		assertEquals(customer.getFirstName(),"Rider");
		
	}
	
	@Test
	public void testForCustomerUsername() {
		Customer customer = new Customer();
		customer.setUsername("Hawking YH");
		assertEquals(customer.getUsername(),"Hawking YH");
		
	}
	
	
	@Test
	public void testForCustomerAddress() {
		Customer customer = new Customer();
		customer.setAddress("City-ABC");
		assertEquals(customer.getAddress(),"City-ABC");
		
	}
	
	@Test
	public void testForCustomerPasswordVerification() {
		Customer customer = new Customer("Tom","Rider","CityABC","Hawking","admin123");
		boolean expected = true;
		assertEquals(customer.verifyPassword("admin123"),expected);
		
	}

	@Test
	public void testForDuplicateCustomer() {
		
		Customer customer = new Customer("Tom","Rider","CityABC","Hawking","admin123");
		Customer customer2 = new Customer();
		int expected = 0;
		assertEquals(customer.compareTo(customer2),expected);
	}
	
	@Test
	public void testTotalOrdersTakenByCustomer() {
		
		Customer customer = new Customer("Tom","Rider","CityABC","Hawking","admin123");
		Order order = new Order();
		customer.insertOrder(order);
		int expected = 1;
		assertEquals(customer.getNumberOfOrders(),expected);
	}
}
