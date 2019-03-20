package UI;

import java.util.Scanner;

import ADT.BST;
import User.*;


/*******

Log in:     
- Log in as customer
- Create a new account (added to the file)
- Log in as a guest
- Log in as an Employee

For Customer User: 
- Search for a product
	- Find and display one record using the primary key
	- Find and display one record using the secondary key
- List Database of Products
	- List data sorted by primary key
	- List data sorted by secondary key
- Place an Order
	- Overnight Shipping 
	- Rush Shipping
	- Standard Shipping
- View Purchases
	- View shipped orders
	- View unshipped orders
- Quit

For employee user:
- Search for a customer by name
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
 *
 */
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
		System.out.println("****Please choose one of the option from the following: ");
		System.out.println("****S: Search for a product");            
		System.out.println("****L: Log In ");                              
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
				System.out.println("----Sorry, invalid output. ");
				continue; 
			}
		}
	}
	
	public void loggedinmenus()
	{
		String input = ""; boolean isvalid = false; 

		System.out.println("****Please choose one of the option from the following: ");     
		System.out.println("****S: Search for a product");     
		System.out.println("****L: List Products ");
		System.out.println("****V: View my shopping cart");  
		System.out.println("****Q: Log out");
		
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
			else if (input.equalsIgnoreCase("L"))
			{
				String choice;
				System.out.println("****Would you like to list all the products by product name or by product ID? ");
				System.out.println("****Press n/N for name and i/I for ID");
				choice = scanner.next();
				if (choice.equalsIgnoreCase("n"))
				{
					BST temp = User.displayProductPrimarily(); 
					for (int i = 0; i < temp.getProducts().size(); i++)
					{
						System.out.println("Product Name: " + temp.getProducts().get(i).getName());
						System.out.println("Product ID: " + temp.getProducts().get(i).getProductId());
						System.out.println("Product Price: " + temp.getProducts().get(i).getUnitPrice());
						System.out.println("Product Cost: " + temp.getProducts().get(i).getCost());
						System.out.println("--------------------------------------------------------------");
					}
				}
				else if (choice.equalsIgnoreCase("i"))
				{
					BST temp = User.displayProductSecondary(); 
					for (int i = 0; i < temp.getProducts().size(); i++)
					{
						System.out.println("Product Name: " + temp.getProducts().get(i).getName());
						System.out.println("Product ID: " + temp.getProducts().get(i).getProductId());
						System.out.println("Product Price: " + temp.getProducts().get(i).getUnitPrice());
						System.out.println("Product Cost: " + temp.getProducts().get(i).getCost());
						System.out.println("--------------------------------------------------------------");
					}		
				}
				else
				{
					System.out.println("----Wrong input! Assume you are going to list by product name. ");
					BST temp = User.displayProductPrimarily(); 
					for (int i = 0; i < temp.getProducts().size(); i++)
					{
						System.out.println("Product Name: " + temp.getProducts().get(i).getName());
						System.out.println("Product ID: " + temp.getProducts().get(i).getProductId());
						System.out.println("Product Price: " + temp.getProducts().get(i).getUnitPrice());
						System.out.println("Product Cost: " + temp.getProducts().get(i).getCost());
						System.out.println("--------------------------------------------------------------");
					}
				}
				isvalid = true; 
			}
			else
			{
				System.out.println("----Sorry, invalid output. ");
				continue; 
			}
		}
	}
	
	public void employeeloggedinmenus()
	{
		String input = ""; boolean isvalid = false; 
		System.out.println("****Please choose one of the option from the following: ");     
		System.out.println("****S: Search for a product");     
		System.out.println("****L: List out Product");  
		System.out.println("****C: Search for a customer");  
		System.out.println("****D: Delivery Order");
		System.out.println("****Q: Log out");

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
				System.out.println("----Sorry, invalid output. ");
				continue; 
			}
		}
	}
}
