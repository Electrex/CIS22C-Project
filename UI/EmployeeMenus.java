package UI;

import User.*;
import ADT.*;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import Modules.*;

public class EmployeeMenus {
	
	private Scanner scanner = new Scanner(System.in); 
	
	public void deliverorder()
	{
		System.out.println("****Sending Orders");
		String in; 
		
		PriorityQueue<Order> unshippedorders = Server.viewOrders();
		for (int i = 1; i < unshippedorders.get_size(); i ++)
		{
			System.out.println("Customer Name: " + unshippedorders.get_element(i).getCustomerName());
			System.out.println("Order ID: " + unshippedorders.get_element(i).getOrderID());
			System.out.println("Order Date: " + unshippedorders.get_element(i).getOrderDate());
			System.out.println("Shipment Date: " + unshippedorders.get_element(i).getShipDate());
			System.out.println("Shipment Type: " + unshippedorders.get_element(i).getShipmentType());
			
			for (int j = 0; j < unshippedorders.get_element(i).getProduct().size(); j++)
			{
				System.out.println("Product Name: " + unshippedorders.get_element(i).getProduct().get(j)
				+ " Product Quantities: " + unshippedorders.get_element(i).getQuantity().get(j));
			}
			System.out.println("_____________________________________________________________________________\n");

			boolean isvalid = false; 
			while (!isvalid)
			{
				System.out.println("**** Press S to ship the order: ");
				in = scanner.next();
				if (in.equals("S"))
				{
					Server.sendOrder(User.getorders().get_element(i));
					System.out.println("The order has shipped! ");
					isvalid = true; 
				}
				else
				{
					System.out.println("---- Invalid Input! Please try again! ");
				}
			}
		}
	}
	
	public void searchcustomer()
	{
		System.out.print("\nPress (L) to list out all customers or (S) to search for a specific customer: ");
		String choice = scanner.next();
		
		
		while(!choice.equalsIgnoreCase("L") && !choice.equalsIgnoreCase("S"))
		{
		    System.out.println("\nInvalid input!");
		    System.out.print("\nPress (L) to list out all customers or (S) to search for a specific customer: ");
	        choice = scanner.next();
		}
		
		if (choice.equalsIgnoreCase("L"))
		{
		    System.out.println();
		    
			ArrayList<Customer> temp = User.getcustomers().getObjects();
			
			for(int i = 0; i < temp.size(); i++)
			{
			    temp.get(i).displayCustomer();
			    System.out.println();
			}
			
		}
		
		else if (choice.equalsIgnoreCase("S"))
		{
			String fname, lname; 
			while (true)
			{
				System.out.print("\nEnter customer's first name or press q to quit: ");
				fname = scanner.next();
				if (fname.equalsIgnoreCase("q"))
					break;
				System.out.print("Enter customer's last name or press q to quit: ");
				lname = scanner.next();
				if (lname.equalsIgnoreCase("q"))
					break;
				
				if (Server.searchCustomer(fname, lname) != null)
				{
				    System.out.println();
				    Customer temp = Server.searchCustomer(fname, lname);
				    temp.displayCustomer();
				    break;
				}
				else
				{
				    System.out.println("\nThe customer " + fname + " " + lname + " does not exist!");
                    System.out.println("Please enter the customer's information again.");
				}
			}
		}
	}
	
	public void addproduct()
	{
		
		String productname, productid, description, manufacturer; 
		double productprice, productcost;
		System.out.print("\nPlease enter product's name: ");
		productname = scanner.nextLine();
		System.out.print("Please enter product's ID: ");		
		productid = scanner.nextLine();
		System.out.print("Please enter product's unit price: ");
		while(true) {
			try {
				productprice = scanner.nextDouble();
				scanner.nextLine();
				break;
			}
			catch (InputMismatchException e) {
				System.out.println("Please enter a valid price.");
			}
		}
		
		System.out.print("Please enter product's cost: ");
		
		while(true) {
			try {
				productcost = scanner.nextDouble();
				scanner.nextLine();
				break;
			}
			catch (InputMismatchException e) {
				System.out.println("Please enter a valid cost.");
			}
		}
		System.out.print("Please enter product's description: ");		
		description = scanner.nextLine();		
		System.out.print("Please enter product's manufacturer: ");		
		manufacturer = scanner.nextLine();
		
		// String name, String productId, double cost, double unitPrice, String manufacturer, String description
		User.adddata("b", new Product(productname, productid, productcost, productprice, manufacturer, description));
	}
	
	public void searchproduct()
	{
		String choice;
		System.out.print("\nPress (N) to search product by name or (I) to search product by ID: ");
		choice = scanner.next();
		
		while(!choice.equalsIgnoreCase("n") && !choice.equalsIgnoreCase("i"))
		{
		    System.out.println("Invalid input!");
		    System.out.print("Press (N) to search product by name or (I) to search product by ID: ");
		    choice = scanner.next();
		}
		
		
		if (choice.equalsIgnoreCase("n"))
		{
			String name = "";
			
			while(true)
			{
				System.out.print("Please enter the product name or press Q if you want to stop: ");
				name = scanner.next();
				
				if (name.equalsIgnoreCase("q"))
				{
					break; 
				}
				
				if (User.primaryProductSearch(name) != null)
				{
					System.out.println("\nProduct name: " + User.primaryProductSearch(name).getName());
					System.out.println("Product ID: " + User.primaryProductSearch(name).getProductId());
					System.out.println("Product Price: $" + User.primaryProductSearch(name).getUnitPrice());
					System.out.println("Product Cost: $" + User.primaryProductSearch(name).getCost());
					System.out.print("\nPress D to delete or press any key to continue: ");
					
					scanner.nextLine();
					String userAnswer = scanner.nextLine();
					if (userAnswer.equalsIgnoreCase("D"))
					{
						System.out.println("Deleted " + User.primaryProductSearch(name).getName() + "!\n");
						User.removedata("b", User.primaryProductSearch(name));
					}
					else
					{
					    break;
					}
				}
				else
				{
					System.out.println("There are no products with this name. ");
				}
			}
		}
		else 
		{
			String id = "";
			
			while(true)
			{
				System.out.print("Please enter the product ID or press Q if you want to stop: ");
				id = scanner.next();
				
				if (id.equalsIgnoreCase("q"))
				{
					break; 
				}
				
				if (User.secondaryProductSearch(id) != null)
				{
					System.out.println("\nProduct name: " + User.secondaryProductSearch(id).getName());
					System.out.println("Product ID: " + User.secondaryProductSearch(id).getProductId());
					System.out.println("Product Price: $" + User.secondaryProductSearch(id).getUnitPrice());
					System.out.println("Product Cost: $" + User.secondaryProductSearch(id).getCost());
					System.out.print("\nPress D to delete or (Enter) to continue: ");
					scanner.nextLine();
					String userAnswer = scanner.nextLine();
					if (userAnswer.equalsIgnoreCase("D"))
					{
					    System.out.println("Deleted " + User.secondaryProductSearch(id).getName() + "!\n");
					    User.removedata("b", User.secondaryProductSearch(id));
					}
					else
					{
					    break;
					}
				}
				else
				{
					System.out.println("There are no products with this ID. ");
				}
			}
		}
	}
	
	public void listproduct()
	{
		String choice;
	    System.out.println("\nPress (N) to search product by name or (I) to search product by ID: ");
		choice = scanner.next();
		if (choice.equalsIgnoreCase("n"))
		{
			BST temp = User.getproducts();
			temp.sortByPrimary();
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
			BST temp = User.getproducts();
			temp.sortBySecondary();
			for (int i = 0; i < temp.getProducts().size(); i++)
			{
				System.out.println("Product Name: " + temp.getProducts().get(i).getName());
				System.out.println("Product ID: " + temp.getProducts().get(i).getProductId());
				System.out.println("Product Price: " + temp.getProducts().get(i).getUnitPrice());
				System.out.println("Product Cost: " + temp.getProducts().get(i).getCost());
				System.out.println("--------------------------------------------------------------");
			}		
		}
	}
}
