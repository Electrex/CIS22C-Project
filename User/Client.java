package User;

import ADT.*;
import Modules.*; 

public class Client {
	
	public Client()
	{

	}
	
	/**********
	 * verify the logging in information that the user has passed in. 
	 * @param username the username that is being passed in. 
	 * @param password the password that is being passed in. 
	 * @return true if the passed in username and password is either in customerlist or employeelist
	 * 		   false if the passed in username and password is neither in customerlist nor employeelist 
	 */
	public static boolean verifyLogInInformation(String username, String password)
	{	
		Customer searchvalue = new Customer(username, password);
		
		for (int i = 0; i < User.getcustomers().getNumElements(); i ++)
			if (User.getcustomers().search(searchvalue) != -1)
				return true; 
			else
				continue;
		
		Employee searchvalue1 = new Employee(username, password);
		for (int i = 0; i < User.getemployees().getLength(); i++)
			if (User.getemployees().linearSearch(searchvalue1) != -1)
				return true;
			else
				continue;
		return false;
	}
	
	/********
	 * create a new customer object using the information passed into the parameter
	 * Insert the object into the customer list 
	 */
	public static void createnewaccount(/* take customer properties */)
	{
		// TODO Albert: fill up the parameter and complete the method
	}
	
	/***************
	 * create a new order object using the information passed into the parameter
	 * Insert the object into the order list 
	 */
	public static void placeOrder(/* take a order properties */)
	{
		// TODO Albert: fill up the parameter and complete the method
	}
	
	/************
	 * Show all the past orders 
	 */
	public static List<Order> viewPurchases(Customer customer)
	{
		return customer.getOrders();
	}
}


