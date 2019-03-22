package User;

import java.util.ArrayList;

import ADT.*;
import IO.*;
import Modules.*;

/******
- Search for a customer by name      searchCustomer(String firstname, String lastname)
- Display unsorted customer information, including first and last name, address, order history    displayCustomer() 
- View Orders by Priority     vieworder()
- Ship an Order      shiporder()
- List Database of Products      
	- List data sorted by primary key
	- List data sorted by secondary key   
- Add a new product     addProduct(double unitPrice, String name, String productId, double cost)
- Remove a product      removeproduct(Product product)
- Quit         logout() 

 * @author albertliu
 */


public class Server {
	
	private boolean isloggedin = false;
	private Employee employee;
	
	/******
	 * This is when the employee has already logged in. 
	 * @param employee the employee who uses this
	 */
	public Server (Employee employee)
	{	
		this.employee = employee;
		isloggedin = true; 
	}
	
	/*******
	 * This is when the employee is in guest mode. 
	 */
	public Server ()
	{	
		this.employee = null;
		isloggedin = false; 
	}
	
	/*******
	 * this gives other sources the access of customer that is currently logged in. 
	 * @return customer
	 */
	public Employee getemployee()
	{
		return employee; 
	}
	
	/*****
	 * initial employee variable
	 * set isloggedin to true 
	 * @param username
	 * @param password
	 */
	public void login(String username, String password)
	{
		List<Employee> employeelist = User.getemployees();
		int idx = employeelist.linearSearch(new Employee(username, password));
		employeelist.moveToIndex(idx);
		employee = employeelist.getIterator();
		isloggedin = true; 
	}

	public boolean isloggedin()
	{
		return isloggedin; 
	}
	
	public void logout()
	{
		employee = null; 
		isloggedin = false; 
	}
	
	/******** 
	 * Returns an arraylist of customers 
	 * @return an arraylist that holds all the customers. 
	 */
	public static ArrayList<Customer> displayCustomer()
	{
		String[] datas = User.getcustomers().toString().split("\n"); // Call toString method of the hash map 
		String[] data;
		ArrayList<Customer> customers = new ArrayList<Customer>();
		for (int i = 0; i < datas.length; i++)
		{
			data = datas[i].split(","); 		// Parse through the string and get customer properties
			customers.add(new Customer(data[0], data[1], data[2], data[3], data[4])); // Add the customer object to the list
		}
		
		return customers;
	}
	
	/**********
	 * verify the logging in information that the user has passed in. 
	 * @param username the username that is being passed in. 
	 * @param password the password that is being passed in. 
	 * @return true if the passed in username and password is found
	 * 		   false if the passed in username and password is not found 
	 */
	public static boolean verifyLogInInformation(String username, String password)
	{	
		Employee searchvalue1 = new Employee(username, password);
		for (int i = 0; i < User.getemployees().getLength(); i++)
			if (User.getemployees().linearSearch(searchvalue1) != -1)
				return true;
			else
				continue;
		return false;
	}
	
	/******
	 * Search the customer by the full name 
	 * If the customer name is not found or is in a different format, then return null. 
	 * @param firstname
	 * @param lastname
	 * @return
	 */
	public static Customer searchCustomer(String firstname, String lastname)
	{
		Customer searchobj = new Customer(firstname, lastname); 
		if (User.getcustomers().search(searchobj) != -1) 
		{       
			return User.getcustomers().searchKey(searchobj);      
		} 	      
		else        
		{        
			return null;         
		}        
	}        
	         
	/*******
	 * return the entire list of orders.         
	 * @return a priority queue with all the unshipped orders
	 */
	public static PriorityQueue<Order> viewOrders()
	{	
		PriorityQueue<Order> orders = new PriorityQueue<Order>();
		for (int i = 0; i < User.getorders().get_size(); i++)
		{
			if (!((Order) User.getorders().get_element(i)).getIsShipped()) // it is false means that the order is not yet being shipped
			{
				orders.insert((Order)(User.getorders().get_element(i)));
			}
		}
		return orders;
	}
	
	/*****
	 * sending orders, the first order will be sent. 
	 * 
	 */
	public static void sendOrder(Order sentorder) 
	{
		sentorder.toggleIsShipped();
	}
	
	/*******
	 * Add the product to the Product list 
	 * @param unitPrice
	 * @param name
	 * @param productId
	 * @param cost
	 */
	public static void addProduct(double unitPrice, String name, String productId, double cost, String description, String manu)
	{
		User.adddata("b", new Product(name, productId, cost, unitPrice, manu, description));
	}
	
	/******
	 * Remove the product 
	 * @param product
	 */
	public static void removeProduct(Product product)
	{
		User.removedata("b", product);
	}
}
