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
				System.out.println();
				System.out.println("Product name: " + searchedoutput.getName());
				System.out.println("Product description: " + searchedoutput.getDescription());
				System.out.println("Product ID: " + searchedoutput.getProductId());
				System.out.println("Product price: $" + searchedoutput.getUnitPrice()); 
				System.out.println();
				
				boolean isvalid = true;
				while (isvalid)
				{
				    System.out.print("Would you like to add this to the shopping cart (Y/N)? ");
					String yesorno = scanner.nextLine();
					
					if (yesorno.equalsIgnoreCase("y"))
					{
					    String quantity = "";
					    
						while(true)
						{
						    try
						    {
						        System.out.printf("\nHow many %s would like to put into the shopping cart? ", searchedoutput.getName());
		                        quantity = scanner.nextLine();
		                        
		                        if(Integer.parseInt(quantity) <= 0)
		                        {
		                            throw new NumberFormatException();
		                        }
		                        Welcome.getClient().addtoshoppingcart(searchedoutput, Integer.parseInt(quantity));
		                        
		                        System.out.println("\n" + quantity + " " + searchedoutput.getName() + " have been placed in your shopping cart!");
                                isvalid = false;
                                break;
						    }
	                        catch(NumberFormatException e)
	                        {
	                          System.out.println("Please enter a valid quantity");
	                          quantity = scanner.nextLine();
	                        }
						}
					}
					else if (yesorno.equalsIgnoreCase("n"))
					{

						System.out.println("Okay! Going back to the homepage...");
						isvalid = false;
					} 
					else
					{ 
						System.out.println("Invalid input, please try again! ");
						System.out.println();
					    continue;

					}
				}
				
				break;
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
