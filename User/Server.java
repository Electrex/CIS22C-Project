package User;

import java.util.ArrayList;

import ADT.*;
import IO.*;
import Modules.*;

/******
 * - Search for a customer by name

- Display unsorted customer information, including first and last name, address, phone number, order history

- View Orders by Priority

- Ship an Order

- List Database of Products

- List data sorted by primary key

- List data sorted by secondary key

         - Add a new product
         - Remove a product
         - Quit
 * @author albertliu
 */


public class Server {
	
	private static boolean isloggedin = false;
	
	public Server ()
	{	

	}
	
	public static void login()
	{
		isloggedin = true; 
	}

	public static boolean isloggedin()
	{
		return isloggedin; 
	}
	
	public static void logout()
	{
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

		if (User.getcustomers().search(new Customer(firstname, lastname, "", "")) != -1) // leaving username password empty
		{
			// TODO Search in Hash will return the index, but I can't really do anything with the index. 
		} 	
		else
		{
			return null; 
		}
	}
	
	/*******
	 * return the entire list of orders. 
	 * @return
	 */
	public static PriorityQueue viewOrders()
	{	
		PriorityQueue orders = new PriorityQueue();
		for (int i = 0; i < User.getorders().get_size(); i++)
		{
			if (!User.getorders().get_element(i).getIsShipped()) // it is false means that the order is not yet being shipped
			{
				orders.insert(User.getorders().get_element(i));
			}
		}
		return orders;
	}
	
	/*****
	 * sending orders, the first order will be sent. 
	 */
	public static void sendOrder(Order sentorder) 
	{
		PriorityQueue temp = User.getorders();
		temp.remove(User.getorders().sort().indexOf(sentorder));
		User.setOrderslist(temp); 
	}
	
	/*******
	 * Add the product to the Product list 
	 * @param unitPrice
	 * @param name
	 * @param productId
	 * @param cost
	 */
	public static void addProduct(double unitPrice, String name, String productId, double cost)
	{
		User.adddata("b", new Product(unitPrice, name, productId, cost));
	}
	
	public static void removeProduct(Product product)
	{
		User.removedata("b", product);
	}
}
