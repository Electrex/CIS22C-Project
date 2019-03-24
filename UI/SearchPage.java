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
			    System.out.println("Invalid option! Please enter again.");
				continue;
			}
		}
	}
	
	public void namesearch()
	{
		String name = "";
		
		while(true)
		{
			System.out.println("\nPlease enter the product name or press Q to exit: ");
			name = scanner.nextLine();
			Product searchedoutput = User.primaryProductSearch(name);
			
			if (name.equalsIgnoreCase("q"))
			{
				break; 
			}
			
			else if (searchedoutput != null)
			{
				System.out.println("Product name: $" + searchedoutput.getName());
				System.out.println("Product ID: $" + searchedoutput.getProductId());
				System.out.println("Product Price: $" + searchedoutput.getCost()); 
				System.out.println("Would you like to add this to the shopping cart? ");
				boolean isvalid = false;
				while (!isvalid)
				{
					System.out.println("****Y for Yes and N for No. ");
					String yesorno = scanner.nextLine();
					if (yesorno.equalsIgnoreCase("y"))
					{
						System.out.println("**** Enter the amount of this product you would like to put into the shopping cart: ");
						String quantity = scanner.nextLine();
						if (Integer.parseInt(quantity) >= 1)
						{
							Welcome.getClient().addtoshoppingcart(searchedoutput, Integer.parseInt(quantity));
						}
						else
						{
							System.out.println("----Invalid input, please try again! ");
						}
					}
					else if (yesorno.equalsIgnoreCase("n"))
					{
						System.out.println("**** Okay! Back to homepage");
					} 
					else
					{ 
						System.out.println("----Invalid input, please try again! ");
					}
				}
			}
			else
			{
				System.out.println("There are no product with this name. ");
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
				System.out.println("Product name: $" + User.secondaryProductSearch(id).getName());
				System.out.println("Product ID: $" + User.secondaryProductSearch(id).getProductId());
				System.out.println("Product Price: $" + User.secondaryProductSearch(id).getCost());
			}
			else
			{
				System.out.println("There are no product with this ID. ");
			}
		}
	}
}
