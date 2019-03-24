package UI;

import User.*; 
import java.util.Scanner;

import Modules.Product;

public class SearchPage {
	private Scanner scanner = new Scanner(System.in);
	
	public void Search()
	{
		boolean backtohomepage = false;
		while (!backtohomepage)
		{
			String choice;
			System.out.println("\nSearch a product by name or ID or press Q to go back to Homepage");
			System.out.print("Press (N) for name and (I) for ID: ");
			choice = scanner.nextLine();

			if (choice.equalsIgnoreCase("n"))
			{
				namesearch();
			}
			else if (choice.equalsIgnoreCase("i"))
			{
				idsearch();
			}
			else if (choice.equalsIgnoreCase("q"))
			{
				backtohomepage = true;
			}
			else  
			{
			    System.out.println("Invalid option! Please try again.");
				continue;
			}
		}
	}
	
	public void namesearch()
	{
		String name = "";
		
		while(true)
		{
			System.out.print("\nPlease enter the product name or press Q to exit: ");
			name = scanner.nextLine();
			Product searchedoutput = User.primaryProductSearch(name);
			
			if (name.equalsIgnoreCase("q"))
			{
				break; 
			}	
			else if (searchedoutput != null)
			{

				System.out.println("Product name: " + searchedoutput.getName());
				System.out.println("Product description: " + searchedoutput.getDescription());
				System.out.println("Product ID: " + searchedoutput.getProductId());
				System.out.println("Product price: $" + searchedoutput.getUnitPrice()); 
				System.out.println("Would you like to add this to the shopping cart? ");
				boolean isvalid = true;
				while (!isvalid)
				{
					System.out.println("(Y) for Yes and (N) for No. ");
					String yesorno = scanner.nextLine();
					if (yesorno.equalsIgnoreCase("y"))
					{
						System.out.printf("How many %s would like to put into the shopping cart? ", searchedoutput.getName());
						System.out.println();
						String quantity = scanner.nextLine();
						if (Integer.parseInt(quantity) >= 1)
						{
							Welcome.getClient().addtoshoppingcart(searchedoutput, Integer.parseInt(quantity));
						}
						else
						{

						  System.out.println("Please enter a valid quantity");

						}
					}
					else if (yesorno.equalsIgnoreCase("n"))
					{

						System.out.println("Okay! Going back to the homepage...");
					} 
					else
					{ 
						System.out.println("Invalid input, please try again! ");

					    isvalid = false;

					}
				}
			}
			else
			{
				System.out.println("There are no products with this name. ");
			}
		}
	}
	
	public void idsearch()
	{
		String id = "";
		
		while(true)
		{
			System.out.println("Please enter the product ID or press Q if you want to stop: ");
			id = scanner.nextLine();
			
			if (!id.equalsIgnoreCase("q"))
			{
				break; 
			}
			
			if (User.secondaryProductSearch(id) != null)
			{
				System.out.println("Product ID: " + User.secondaryProductSearch(id).getProductId());
				System.out.println("Product name: " + User.secondaryProductSearch(id).getName());
				System.out.println("Product description: " + User.secondaryProductSearch(id).getDescription());
				System.out.println("Product price: $" + User.secondaryProductSearch(id).getUnitPrice()); 
			}
			else
			{
				System.out.println("There are no products with this ID. ");

			}
		}
	}
}
