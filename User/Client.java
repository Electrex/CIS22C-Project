package User;

import ADT.*;

public class Client {
	
	private static BST<Customer> customerlist; 
	private static PriorityQueue<Order> orderslist; 
	private static Hash<Product> productlist; 
	private static List<Employee> employeelist; 
	
	public Client()
	{
		customerlist = new BST<Customer>();
		orderslist = new PriorityQueue<Order>();
		productlist = new Hash<Product>();
		employeelist = new List<Employee>();
	}
	
	
	public static void makeOrder()
	{
		
	}
	
	public static boolean verifyLogInInformation(String username, String password)
	{
		
	}
	
	public static void createnewaccount()
}
