package User;

import java.util.ArrayList;

import ADT.*;
import IO.CustomerIO;
import IO.EmployeeIO;
import Modules.*; 

public class Client {
	
	private boolean isloggedin; 
	private ArrayList<Product> shoppingcart; 
	private ArrayList<Integer> quantities; 
	private Customer customer; 
	
	/******
	 * This is when the customer has already logged in. 
	 * @param c the customer that uses this
	 */
	public Client(Customer c) // already logged in    
	{ 
		this.customer = c; 
		isloggedin = true; 
		quantities = new ArrayList<Integer>(); 
		shoppingcart = new ArrayList<Product>(); 
	}
	
	/*******
	 * This is when the customer is in guest mode, or not logged in. 
	 */
	public Client()  // is not logged in 
	{
		this.customer = null;
		isloggedin = false;
		quantities = new ArrayList<Integer>(); 
		shoppingcart = new ArrayList<Product>();
	}
	
	/*******
	 * this gives other sources the access of customer that is currently logged in. 
	 * @return customer
	 */
	public Customer getcustomer()
	{
		return customer; 
	}
	
	/********
	 * Will add the quantity number of product to the array list
	 * If quantity is less than one, then will automatically add one for quantity. 
	 * @param p
	 * @param quantity
	 */
	public void addtoshoppingcart(Product p, int quantity)
	{
		if (quantity < 1)
		{
			quantities.add(1); // purchase at least one
		}
		shoppingcart.add(p);
	}
	
	public ArrayList<Product> getshoppingcart()
	{
		return shoppingcart;
	}
	
	public ArrayList<Integer> getquantities()
	{
		return quantities;
	}
	
	/***********
	 * log in by swapping the isloggedin status
	 * initialize the customer object with the information related to the username/password
	 * @param username
	 * @param password
	 */
	public void login(String username, String password, int index)
	{
		String [] cusprop; // customer properties 
	    cusprop = CustomerIO.getcustomerfilecontent().get(index).split(","); 
		customer = new Customer(cusprop[0], cusprop[1], cusprop[2], cusprop[3], cusprop[4]); 
											// fname      lname       username    password    address      
		isloggedin = true; 
	}

	public boolean isloggedin()
	{
		return isloggedin; 
	}
	
	/*****
	 * Set the customer back to null
	 * islogged in is swapped back to false 
	 */
	public void logout()
	{
		customer = null; 
		isloggedin = false; 
	}
	
	/***************
	 * Create a new order object using the information passed into the parameter
	 * Insert the object into Customer's past order's queue 
	 * Clear the shopping cart by empty out both parallel arrays
	 */
	public void placeOrder(String shipType)
	{
		Order o = new Order(shoppingcart, quantities, customer.getFullName(), shipType, false);
		User.adddata("p", o);  // MAY NEED TO CHANGE THIS 
		customer.insertOrder(o);
		shoppingcart.clear();     
		quantities.clear();     
	}
	
	/*******
	 * Shows all the past orders    
	 * @return get all the past orders
	 */
	public PriorityQueue<Order> viewPurchases()
	{
		return customer.getOrders();
	}
	
	/********
	 * !!! GUEST USE
	 * create a new customer object using the information passed into the parameter
	 * Insert the object into the customer list 
	 */
	public static void createnewaccount(String firstname, String lastname, String username, String password, String address)
	{
		User.adddata("h", new Customer(firstname, lastname, username, password, address));
	}
	
	/**********
	 * !!! GUEST USE
	 * verify the logging in information that the user has passed in. 
	 * @param username the username that is being passed in. 
	 * @param password the password that is being passed in. 
	 * @return true if the passed in username and password is found
	 * 		   false if the passed in username and password is not found 
	 */
	public static int verifyLogInInformationCustomer(String username, String password)
	{			
	    String temp = username + "," + password;
	    
		for (int i = 0; i < CustomerIO.getcustomerlogininfo().size(); i++)
		{
		    if (CustomerIO.getcustomerlogininfo().get(i).equals(temp))
		    {
		        return i;
		    }
		}
		return -1;
	}
}


