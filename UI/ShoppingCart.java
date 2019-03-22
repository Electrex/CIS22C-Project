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
		System.out.println("************* Your Shopping Cart **************");
		System.out.println("****Here is what you have in your shopping cart: ");
		
		for (int i = 0; i < Welcome.getClient().getshoppingcart().size(); i ++)
		{
			System.out.println((i + 1) + ": ");
			System.out.println("Product Name: " + Welcome.getClient().getshoppingcart().get(i).getName());
			System.out.println("Product ID: " + Welcome.getClient().getshoppingcart().get(i).getProductId());
			System.out.println("Product price: " + Welcome.getClient().getshoppingcart().get(i).getUnitPrice());
			System.out.println("Quantity: " + Welcome.getClient().getquantities().get(i));
			System.out.println("_____________________________________________________________________________\n");
		}
		
		System.out.println("****Do you want to place your order? Y/N");
		String yesorno = scanner.next();
		if (yesorno.equals("Y"))
		{
			placeOrder();
		}
		else
		{
			System.out.println("****Okay! Back to Homepage!");
		}
	}
	
	public void placeOrder()
	{
		double totalprice = 0.0; 
		System.out.println("************** Placing Order ****************");
		System.out.println("****Step 1: Confirm User Information");
		System.out.println("****Please enter your name: ");
		String name = scanner.next();
		System.out.println("****Please enter your address: ");
		String add = scanner.next();
		System.out.println("_____________________________________________________________________________\n");
		System.out.println("****Step 2: Payment method: ");
		String cardid = scanner.next();
		System.out.println("_____________________________________________________________________________\n");
		System.out.println("****Step 3: Shipping method: "); 
		System.out.println("****Please select one from the following by enter the correct number: ");
		System.out.println("****1. Overnight Shipping: $4.99 ");
		System.out.println("****2. Rush Shipping: $2.99 ");
		System.out.println("****3. Standard Shipping: $1.99 ");
		String shipmentmethod = scanner.next();
		
		System.out.println("****Okay, " + name + "! You are all set here is your order confirmation: ");
		for (int i = 0; i < Welcome.getClient().getshoppingcart().size(); i ++)
		{
			System.out.print("Product Name: " + Welcome.getClient().getshoppingcart().get(i).getName());
			System.out.print("    Product price: " + Welcome.getClient().getshoppingcart().get(i).getUnitPrice());
			System.out.print("    Quantity: " + Welcome.getClient().getquantities().get(i) + "\n");
			totalprice += (Welcome.getClient().getshoppingcart().get(i).getUnitPrice())*(Welcome.getClient().getquantities().get(i)); 
		}	
		
		if (shipmentmethod.equals("1"))
		{
			System.out.println("Overnight shhipping: $4.99");
			totalprice += 4.99;
		}
		else if (shipmentmethod.equals("2"))
		{
			System.out.println("Overnight shhipping: $2.99");
			totalprice += 2.99;
		}
		else if (shipmentmethod.equals("3"))
		{
			System.out.println("Overnight shhipping: $1.99");
			totalprice += 1.99;
		}
		else // assume the user has entered the invalid input. 
		{
			System.out.println("Overnight shhipping: $4.99");
			totalprice += 4.99;
		}
		
		System.out.println("Tax: " + totalprice*0.09);
		totalprice*=1.09; 
		System.out.println("Total price: " + totalprice);
		System.out.println("****Press any key to back to homepage! ");
		String anykey = scanner.next();
	}
}
