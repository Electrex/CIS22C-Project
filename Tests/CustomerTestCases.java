package Tests;

import static org.junit.Assert.*;
import Modules.Customer;
import Modules.Employee;
import Modules.Order;
import User.Server;
import User.User;

import org.junit.Test;

import ADT.Hash;
import IO.CustomerIO;


public class CustomerTestCases {

/*	@Test
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
*/	
	@Test
    public void testForSearchCustomer() {
        Customer customer = new Customer("Claire", "Gute");
        Server server = new Server();
        String [] files = {"Customer.csv", 
                "Employee.csv", 
                "Orders.csv", 
                "Product.csv"}; 
        User user = new User(files);
        user.loaddata();
        System.out.println(server.searchCustomer("Claire", "Gute"));
    }
	
	/*
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
		//assertEquals(customer.getNumberOfOrders(),expected);
	}
	
	@Test
	public void testForReadCustomers() {
		
		CustomerIO customer = new CustomerIO("customer.txt");
		Hash<Customer> customers = customer.readfile();
		boolean expected = true;
		assertEquals(customers.getNumElements()>0, expected);
	    
	}
	
	@Test
	public void testForCustomerFileInfo() {
		
		CustomerIO customer = new CustomerIO("customer.txt");
		boolean expected = false;
		assertEquals(CustomerIO.getcustomerfilecontent().isEmpty(), expected);
	}
	*/
}
