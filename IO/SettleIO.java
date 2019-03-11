package IO;

import ADT.*; 

public class SettleIO {
	private String filename, fileExtention, fileType;
	
	private BST<Customer> customerlist; 
	private PriorityQueue<Order> orderslist; 
	private Hash<Product> productlist; 
	private List<Employee> employeelist; 
	
	public SettleIO(String fname)
	{
		customerlist = new BST<Customer>();
		orderslist = new PriorityQueue<Order>();
		productlist = new Hash<Product>();
		employeelist = new List<Employee>();
	}	
	
	
	
	
}
