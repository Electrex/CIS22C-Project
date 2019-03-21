package User;

import java.util.ArrayList;

import ADT.*;
import IO.CustomerIO;
import Modules.*; 

public class Client {
	
	private static boolean isloggedin = false;;
	private static ArrayList<Product> shoppingcart = new ArrayList<Product>();
	private static ArrayList<Integer> quantities = new ArrayList<Integer>(); 
	
	/********
	 * Will add the quantity number of product to the array list
	 * If quantity is less than one, then will automatically add one for quantity. 
	 * @param p
	 * @param quantity
	 */
	public static void addtoshoppingcart(Product p, int quantity)
	{
		if (quantity < 1)
		{
			quantities.add(1); // purchase at least one
		}
		shoppingcart.add(p);
	}
	
	public static ArrayList<Product> getshoppingcart()
	{
		return shoppingcart;
	}
	
	public static ArrayList<Integer> getquantities()
	{
		return quantities;
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
		for (int i = 0; i < CustomerIO.getcustomerfilecontent().size(); i ++)
		{
			if (CustomerIO.getcustomerfilecontent().get(i).contains(username))
				if (CustomerIO.getcustomerfilecontent().get(i).contains(password))
					return true; 
		}
			
		return false;
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
	 * create a new customer object using the information passed into the parameter
	 * Insert the object into the customer list 
	 */
	public static void createnewaccount(String firstname, String lastname, String username, String password, String address)
	{
		User.adddata("h", new Customer(firstname, lastname, username, password, address));
	}
	
	/***************
	 * create a new order object using the information passed into the parameter
	 * Insert the object into the order list 
	 */
	public static void placeOrder(String shipType)
	{
		User.adddata("p", new Order(shoppingcart, quantities, shipType));
	}
	
	/************
	 * Show all the past orders 
	 */
	public static List<Order> viewPurchases(Customer customer)
	{
		return customer.getOrders();
	}
}


