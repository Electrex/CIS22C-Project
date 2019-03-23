package UI;

import User.*;
import ADT.*;
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
		System.out.println("****Would you like to list out all the customers or search for a specific customer: ");
		System.out.print("****Enter L to list out and S to search");
		String choice = scanner.next();
		
		
		while(!choice.equalsIgnoreCase("L") && !choice.equalsIgnoreCase("S"))
		{
		    System.out.println("Invalid input!");
		    System.out.print("****Enter L to list out and S to search");
	        choice = scanner.next();
		}
		
		
		if (choice.equalsIgnoreCase("L"))
		{
			User.getcustomers().toString(); 
		}
		else if (choice.equalsIgnoreCase("S"))
		{
			String fname, lname; 
			while (true)
			{
				System.out.println("****Please enter customer's firstname or press q to quit: ");
				fname = scanner.next();
				if (fname.equalsIgnoreCase("q"))
					break;
				System.out.println("****Please enter customer's lastname or press q to quit: ");
				lname = scanner.next();
				if (lname.equalsIgnoreCase("q"))
					break;
				
				if (Server.searchCustomer(fname, lname) != null)
					System.out.println(Server.searchCustomer(fname, lname).toString());
				else
					System.out.println("----The customer does not exist!");
			}
		}
	}
	
	public void addproduct()
	{
		String productname, productid, description, manufactorer; 
		double productprice, productcost;
		System.out.print("****Please enter product's name: ");
		productname = scanner.nextLine();
		System.out.print("****Please enter product's id: ");		
		productid = scanner.nextLine();
		System.out.print("****Please enter product's unit price: ");
		productprice = scanner.nextDouble();
		scanner.nextLine();
		System.out.print("****Please enter product's cost: ");
		productcost = scanner.nextDouble();
		scanner.nextLine();
		System.out.print("****Please enter product's description: ");		
		description = scanner.nextLine();		
		System.out.print("****Please enter product's manufactorer: ");		
		manufactorer = scanner.nextLine();
		
		// String name, String productId, double cost, double unitPrice, String manufacturer, String description
		User.adddata("b", new Product(productname, productid, productcost, productprice, manufactorer, description));
	}
	
	public void searchproduct()
	{
		String choice;
		System.out.println("****Would you like to search by product name or by product ID? ");
		System.out.print("****Press n/N for name and i/I for ID");
		choice = scanner.next();
		
		while(!choice.equalsIgnoreCase("n") && !choice.equalsIgnoreCase("i"))
		{
		    System.out.println("Invalid input!");
		    System.out.print("****Press n/N for name and i/I for ID");
		    choice = scanner.next();
		}
		
		
		if (choice.equalsIgnoreCase("n"))
		{
			String name = "";
			
			while(true)
			{
				System.out.println("****Please enter the product name or press Q if you want to stop: ");
				name = scanner.next();
				
				if (!name.equalsIgnoreCase("q"))
				{
					break; 
				}
				
				if (User.primaryProductSearch(name) != null)
				{
					System.out.println("Product name: $" + User.primaryProductSearch(name).getName());
					System.out.println("Product ID: $" + User.primaryProductSearch(name).getProductId());
					System.out.println("Product Price: $" + User.primaryProductSearch(name).getUnitPrice());
					System.out.println("Product cost: $" + User.primaryProductSearch(name).getCost());
					System.out.println("****Press D to delete, press any other keys to move on");
					if (scanner.next().equalsIgnoreCase("D"))
					{
						System.out.println("Deleted!");
						User.removedata("b", User.primaryProductSearch(name));
					}
				}
				else
				{
					System.out.println("----There are no product with this name. ");
				}
			}
		}
		else if (choice.equalsIgnoreCase("i"))
		{
			String id = "";
			
			while(true)
			{
				System.out.println("****Please enter the product ID or press Q if you want to stop: ");
				id = scanner.next();
				
				if (!id.equalsIgnoreCase("q"))
				{
					break; 
				}
				
				if (User.secondaryProductSearch(id) != null)
				{
					System.out.println("Product name: $" + User.secondaryProductSearch(id).getName());
					System.out.println("Product ID: $" + User.secondaryProductSearch(id).getProductId());
					System.out.println("Product price: $" + User.primaryProductSearch(id).getUnitPrice());
					System.out.println("Product cost: $" + User.primaryProductSearch(id).getCost());
					System.out.println("****Press D to delete, press any other keys to move on");
					if (scanner.next().equalsIgnoreCase("D"))
					{
						System.out.println("Deleted!");
						User.removedata("b", User.secondaryProductSearch(id));
					}
				}
				else
				{
					System.out.println("----There are no product with this ID. ");
				}
			}			
		}
		else
		{
			System.out.println("----Wrong input! Assume you are going to search by product name. ");
			
			String name = "";
			
			while(true)
			{
				System.out.println("****Please enter the product name or press Q if you want to stop: ");
				name = scanner.next();
				
				if (!name.equalsIgnoreCase("q"))
				{
					break; 
				}
				
				if (User.primaryProductSearch(name) != null)
				{
					System.out.println("Product name: $" + User.primaryProductSearch(name).getName());
					System.out.println("Product ID: $" + User.primaryProductSearch(name).getProductId());
					System.out.println("Product Price: $" + User.primaryProductSearch(name).getUnitPrice());
					System.out.println("Product cost: $" + User.primaryProductSearch(name).getCost());
					System.out.println("****Press D to delete, press any other keys to move on");
					if (scanner.next().equalsIgnoreCase("D"))
					{
						System.out.println("Deleted!");
						User.removedata("b", User.primaryProductSearch(name));
					}
				}
				else
				{
					System.out.println("----There are no product with this name. ");
				}
			}
		}
	}
	
	public void listproduct()
	{
		String choice;
		System.out.println("****Would you like to list all the products by product name or by product ID? ");
		System.out.print("****Press n/N for name and i/I for ID");
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
