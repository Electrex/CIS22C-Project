package UI;

import java.util.Scanner;

import ADT.*;
import Modules.*;
import User.*;

public class ShoppingCart {
	private Scanner scanner = new Scanner(System.in);

	public void viewOrder()
	{
		System.out.println("********** " + Welcome.getClient().getcustomer().getFullName() + "'s Order **********");
		PriorityQueue<Order> allcusorders = Welcome.getClient().getcustomer().getOrders();
		PriorityQueue<Order> shippedorders = new PriorityQueue<Order>();
		PriorityQueue<Order> unshippedorders = new PriorityQueue<Order>();
		
		for (int idx = 0; idx < allcusorders.get_size(); idx++)
		{
			if (allcusorders.get_element(idx).getIsShipped())
			{
				shippedorders.insert(allcusorders.get_element(idx));
			}
			else
			{
				unshippedorders.insert(allcusorders.get_element(idx));
			}
		}
		
		System.out.println("\n\n******** Here are orders you have shipped *********");
		for (int i = 0; i < shippedorders.get_size(); i++)
		{
			System.out.println("Order ID: " + shippedorders.get_element(i).getOrderID());
			System.out.println("Order Date: " + shippedorders.get_element(i).getOrderDate());
			System.out.println("Shipment Date: " + shippedorders.get_element(i).getShipDate());
			System.out.println("Shipment Type: " + shippedorders.get_element(i).getShipmentType());
			
			for (int j = 0; j < shippedorders.get_element(i).getProduct().size(); j++)
			{
				System.out.println("Product Name: " + shippedorders.get_element(i).getProduct().get(j)
				+ " Product Quantities: " + shippedorders.get_element(i).getQuantity().get(j));
			}
			System.out.println("_____________________________________________________________________________\n");
		}
		
		System.out.println("\n\n******** Here are orders you have not shipped *********");
		for (int i = 0; i < unshippedorders.get_size(); i++)
		{
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
		}
	}
	
	public void displayshoppingcart()
	{
		System.out.println("\n************* Your Shopping Cart **************");
		
		if(Welcome.getClient().getshoppingcart().size() == 0)
		{
		    System.out.println("Shopping cart is empty!");
		    return;
		}
		
		for (int i = 0; i < Welcome.getClient().getshoppingcart().size(); i ++)
		{
			System.out.println((i + 1) + ") ");
			System.out.println("Product Name: " + Welcome.getClient().getshoppingcart().get(i).getName());
			System.out.println("Product ID: " + Welcome.getClient().getshoppingcart().get(i).getProductId());
			System.out.println("Product price: $" + Welcome.getClient().getshoppingcart().get(i).getUnitPrice());
			System.out.println("Quantity: " + Welcome.getClient().getquantities().get(i));
			System.out.println("_________________________________________________________________________\n");
		}
		
		System.out.print("Do you want to place your order (Y/N)? ");
		String yesorno = scanner.next();
		if (yesorno.equalsIgnoreCase("Y"))
		{
			placeOrder();
			Welcome.getClient().clearShoppingCart();
		}
	}
	
	public void placeOrder()
	{
	    scanner.nextLine();
		double totalprice = 0.0; 
		System.out.println("\n************** Placing Order ****************");
		System.out.println("Step 1: Confirm User Information");
		System.out.print("Please enter your name: ");
		String name = scanner.nextLine();
		System.out.print("Please enter your address: ");
		String add = scanner.nextLine();
		System.out.println("_______________________________________________________________________\n");
		System.out.print("Step 2: Payment method: ");
		String cardid = scanner.nextLine();
		System.out.println("________________________________________________________________________\n");
		System.out.println("Step 3: Shipping method: "); 
		System.out.println("1) Overnight Shipping: $4.99 ");
		System.out.println("2) Rush Shipping: $2.99 ");
		System.out.println("3) Standard Shipping: $1.99 ");
	      System.out.print("\nPlease select one of the following options: ");
		String shipmentmethod = scanner.nextLine();
		
		System.out.println("\nOrder Confirmation: ");
		for (int i = 0; i < Welcome.getClient().getshoppingcart().size(); i ++)
		{
			System.out.println("Product Name: " + Welcome.getClient().getshoppingcart().get(i).getName());
			System.out.println("    Product price: " + Welcome.getClient().getshoppingcart().get(i).getUnitPrice());
			System.out.println("    Quantity: " + Welcome.getClient().getquantities().get(i) + "\n");
			totalprice += (Welcome.getClient().getshoppingcart().get(i).getUnitPrice())*(Welcome.getClient().getquantities().get(i)); 
		}	
		
		if (shipmentmethod.equals("1"))
		{
			System.out.println("Overnight shipping: $4.99");
			totalprice += 4.99;
		}
		else if (shipmentmethod.equals("2"))
		{
			System.out.println("Overnight shipping: $2.99");
			totalprice += 2.99;
		}
		else if (shipmentmethod.equals("3"))
		{
			System.out.println("Overnight shipping: $1.99");
			totalprice += 1.99;
		}
		else // assume the user has entered the invalid input. 
		{
			System.out.println("Overnight shipping: $4.99");
			totalprice += 4.99;
		}
		
		System.out.printf("Tax: $%.2f", totalprice*0.09);
		System.out.println();
		totalprice*=1.09; 
		System.out.printf("Total price: $%.2f", totalprice);
		System.out.print("\n\nPress any key to back to homepage! ");
		scanner.nextLine();
	}
}
