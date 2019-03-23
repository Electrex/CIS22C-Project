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
 */
public class Welcome {
	
	private Scanner scanner; 
	private static Client client;
	private static Server server;
	private boolean exit;
	
	public Welcome()
	{
		scanner = new Scanner(System.in); 
		client = new Client();
		server = new Server();
		exit = false;
	}
	
	public static Client getClient()
	{
		return client; 
	}
	
	public static Server getServer()
	{
		return server; 
	}
	
	public void welcome()
	{
		System.out.println("********************* Welcome to Dash! *********************");
		start();
	}
	
	public void start()
	{
		while(exit == false)
			if (client.isloggedin())
				loggedinmenus(); 
			else if (server.isloggedin())
				employeeloggedinmenus();
			else 
				guestmenus(); 
	}
	
	public void guestmenus()
	{
		String input = ""; boolean isvalid = false; 
		System.out.println("\n");
		System.out.println("Welcome Guest!"); 
		System.out.println("**** Please choose one of the options from the following: ");
		System.out.println("**** S: Search for a product");            
		System.out.println("**** L: Log In or Create an Account ");   
		System.out.println("**** Q: Quit ");                              
		input = scanner.next();
		while (!isvalid)
		{
			if (input.equalsIgnoreCase("S"))
			{	
				isvalid = true;
				new SearchPage().Search();
			}
			else if (input.equalsIgnoreCase("l"))
			{
				isvalid = true;
				new LogIn().logIn();
			}
			else if (input.equalsIgnoreCase("q"))
			{
				isvalid = true;
				exit = true; 
				System.out.println("Goodbye!");
				break;
			}
			else
			{
				System.out.println("---- Sorry, invalid output. Try again. ");
				input = scanner.next();
			}
		}
	}
	
	public void loggedinmenus()
	{
		String input = ""; boolean isvalid = false; 

		System.out.println("\n\n\n\n");
		System.out.println("Welcome Customer  "); 
		System.out.println("**** Please choose one of the options from the following: ");     
		System.out.println("**** S: Search for a product");     
		System.out.println("**** L: List Products ");
		System.out.println("**** V: View my shopping cart");  
		System.out.println("**** Q: Log out");
		System.out.println("**** O: View my orders");     
		
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
				client.logout();
				System.out.println("You have successfully logged out.");
			}
			else if (input.equalsIgnoreCase("O"))
			{
				new ShoppingCart().viewOrder();
			}
			else if (input.equalsIgnoreCase("L"))
			{
				String choice;
				System.out.println("**** Would you like to list all the products by product name or by product ID? ");
				System.out.println("**** Press n/N for name and i/I for ID");
				choice = scanner.next();
				if (choice.equalsIgnoreCase("n"))
				{
					BST temp = User.displayProductPrimarily(); 
					for (int i = 0; i < temp.getProducts().size(); i++)
					{
						System.out.println("Product Name: " + temp.getProducts().get(i).getName());
						System.out.println("Product ID: " + temp.getProducts().get(i).getProductId());
						System.out.printf("Product Price: $%.2d\n" , temp.getProducts().get(i).getUnitPrice());
						System.out.printf("Product Cost: $%.2d\n", temp.getProducts().get(i).getCost());
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
					System.out.println("---- Invalid input! Assume you are going to list by product name. ");
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
				System.out.println("----Sorry, invalid output. Try again. ");
				input = scanner.next();
				//continue; 
			}
		}
	}
	
	public void employeeloggedinmenus()
	{
		String input = ""; boolean isvalid = false; 
		System.out.println("\n\n\n\n");
		System.out.println("Welcome Employee   "); 
		System.out.println("**** Please choose one of the option from the following: ");     
		System.out.println("**** S: Search for a product");  
		System.out.println("**** A: Add a product");  
		System.out.println("**** L: List out products");  
		System.out.println("**** C: Search for a customer");  
		System.out.println("**** D: Ship order");
		System.out.println("**** Q: Log out");

		EmployeeMenus menu = new EmployeeMenus();
		input = scanner.next();
		while (!isvalid)
		{
			if (input.equalsIgnoreCase("S"))
			{	
				menu.searchproduct();
				isvalid = true;
			}
			else if (input.equalsIgnoreCase("A"))
			{	
				menu.addproduct();
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
				server.logout();
				System.out.println("You have successfully logged out.");
				isvalid = true;
			}
			else
			{
				System.out.println("----Sorry, invalid output. Try again. ");
				input = scanner.next();
				//continue; 
			}
		}
	}
}
