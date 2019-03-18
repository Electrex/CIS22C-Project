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
	
	public Server ()
	{	

	}
	
	/****************
	 * Display Customer list
	 */
	public static ArrayList<Customer> displayCustomer()
	{
		return ;// TODO Eugene: Use printBucket method to return the Customer list in the order they are being stored. 
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

		if (User.getcustomers().search(new Customer(firstname, lastname)) != -1)
		{
					
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
		for (int i = 0; i < User.getorders().heapSize(); i++)
		{
			if (!User.getorders().getElement(i).getisshipped()) // it is false means that the order is not yet being shipped
			{
				orders.insert(User.getorders().getElement(i));
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
