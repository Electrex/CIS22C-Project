package User;

import ADT.*;
import Modules.*; 

public class Client {
	
	private static Hash<Customer> customerlist; 
	private static PriorityQueue orderslist; 
	private static BST<Product> productlist; 
	private static List<Employee> employeelist; 
	
	public Client()
	{
		customerlist = new Hash<Customer>();
		orderslist = new PriorityQueue<Order>();
		productlist = new BST<Product>();
		employeelist = new List<Employee>();
	}
	
	
	public static void makeOrder()
	{
		
	}
	
	public static boolean verifyLogInInformation(String username, String password)
	{
		boolean isvalidusername = false; 
		boolean isvalidpassword = false; 
		
		return isvalidpassword && isvalidusername; 
	}
	
	public static void createnewaccount()
	{
		
	}
}


