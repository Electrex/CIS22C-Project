package UI;

import java.util.Scanner;
import User.*;

public class Welcome {
	
	private Scanner scanner = new Scanner(System.in); 
	
	public void Welcome()
	{
		System.out.println(" *************** Welcome to Dash! *************** ");
		start();
	}
	
	public void start()
	{
		while(true)
			if (Client.isloggedin())
				employeeloggedinmenus();
			else if (Server.isloggedin())
				loggedinmenus();  
			else 
				guestmenus(); 
	}
	
	public void guestmenus()
	{
		String input = ""; boolean isvalid = false; 
		System.out.println("Please choose one of the option from the following: ");
		System.out.println("S: Search for a product"); 
		System.out.println("L: Log In ");
		input = scanner.next();
		while (!isvalid)
		{
			if (input.equalsIgnoreCase("S"))
			{	
				isvalid = true;
				new SearchPage().Search();
			}
			else if (input.equalsIgnoreCase("L"))
			{
				isvalid = true;
				new LogIn().LogIn();
			}
			else
			{
				System.out.println("Sorry, invalid output. ");
				continue; 
			}
		}
	}
	
	public void loggedinmenus()
	{
		String input = ""; boolean isvalid = false; 

		System.out.println("Please choose one of the option from the following: ");     
		System.out.println("S: Search for a product");     
		System.out.println("V: View my shopping cart");  
		System.out.println("Q: Log out");
		
		input = scanner.next();
		while (!isvalid)
		{
			if (input.equalsIgnoreCase("S"))
			{	
				isvalid = true;
				new SearchPage().Search();
			}
			else if (input.equalsIgnoreCase("V"))
			{
				isvalid = true;
				new ShoppingCart().displayshoppingcart();
			}
			else if (input.equalsIgnoreCase("Q"))
			{
				isvalid = true;
				Client.logout();
			}
			else
			{
				System.out.println("Sorry, invalid output. ");
				continue; 
			}
		}
	}
	
	public void employeeloggedinmenus()
	{
		String input = ""; boolean isvalid = false; 
		System.out.println("Please choose one of the option from the following: ");     
		System.out.println("S: Search for a product");     
		System.out.println("L: List out Product");  
		System.out.println("C: Search for a customer");  
		System.out.println("D: Delivery Order");
		System.out.println("Q: Log out");

		EmployeeMenus menu = new EmployeeMenus();
		input = scanner.next();
		while (!isvalid)
		{
			if (input.equalsIgnoreCase("S"))
			{	
				menu.searchproduct();
				isvalid = true;
			}
			else if (input.equalsIgnoreCase("C"))
			{
				menu.searchcustomer();
				isvalid = true;
			}
			else if (input.equalsIgnoreCase("D"))
			{
				isvalid = true;
				menu.deliverorder();
			}			
			else if (input.equalsIgnoreCase("L"))
			{
				menu.listproduct();
				isvalid = true;
			}
			else if (input.equalsIgnoreCase("Q"))
			{
				Server.logout();
				isvalid = true;
			}
			else
			{
				System.out.println("Sorry, invalid output. ");
				continue; 
			}
		}
	}
}
