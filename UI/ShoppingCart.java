package UI;

import java.util.Scanner;

import User.Client;

public class ShoppingCart {
	private Scanner scanner = new Scanner(System.in);

	public void displayshoppingcart()
	{
		System.out.println("************* Your Shopping Cart **************");
		System.out.println("Here is what you have in your shopping cart: ");
		
		for (int i = 0; i < Client.getshoppingcart().size(); i ++)
		{
			System.out.println((i + 1) + ": ");
			System.out.println("Product Name: " + Client.getshoppingcart().get(i).getName());
			System.out.println("Product ID: " + Client.getshoppingcart().get(i).getProductId());
			System.out.println("Product price: " + Client.getshoppingcart().get(i).getUnitPrice());
			System.out.println("Quantity: " + Client.getquantities().get(i));
			System.out.println("-----------------------------------------------------------");
		}
		
		System.out.println("Do youw want to place your order? Y/N");
		String yesorno = scanner.next();
		if (yesorno.equals("Y"))
		{
			placeOrder();
		}
		else
		{
			System.out.println("Okay! Back to Homepage!");
		}
	}
	
	public void placeOrder()
	{
		double totalprice = 0.0; 
		System.out.println("************** Placing Order ****************");
		System.out.println("Step 1: Confirm User Information");
		System.out.println("Please enter your name: ");
		String name = scanner.next();
		System.out.println("Please enter your address: ");
		String add = scanner.next();
		System.out.println("-----------------------------------------------------------");
		System.out.println("Step 2: Payment method: ");
		String cardid = scanner.next();
		System.out.println("-----------------------------------------------------------");
		System.out.println("Step 3: Shipping method: "); 
		System.out.println("Please select one from the following by enter the correct number: ");
		System.out.println("1. Overnight Shipping: $4.99 ");
		System.out.println("2. Rush Shipping: $2.99 ");
		System.out.println("3. Standard Shipping: $1.99 ");
		String shipmentmethod = scanner.next();
		
		System.out.println("Okay, " + name + "! You are all set here is your order confirmation: ");
		for (int i = 0; i < Client.getshoppingcart().size(); i ++)
		{
			System.out.print("Product Name: " + Client.getshoppingcart().get(i).getName());
			System.out.print("    Product price: " + Client.getshoppingcart().get(i).getUnitPrice());
			System.out.print("    Quantity: " + Client.getquantities().get(i) + "\n");
			totalprice += (Client.getshoppingcart().get(i).getUnitPrice())*(Client.getquantities().get(i)); 
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
		System.out.println("Press any key to back to homepage! ");
		String anykey = scanner.next();
	}
}
