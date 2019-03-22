package UI;

import User.*; 
import java.util.Scanner;

public class SearchPage {
	private Scanner scanner = new Scanner(System.in);
	
	public void Search()
	{
		String choice;
		System.out.println("Would you like to search by product name or by product ID? ");
		System.out.println("Press n/N for name and i/I for ID");
		choice = scanner.next();
		if (choice.equalsIgnoreCase("n"))
		{
			namesearch();
		}
		else if (choice.equalsIgnoreCase("i"))
		{
			idsearch();
		}
		else
		{
			System.out.println("Wrong input! Assume you are going to search by product name. ");
			namesearch();
		}
	}
	
	public void namesearch()
	{
		String name = "";
		
		while(true)
		{
			System.out.println("Please enter the product name or press Q if you want to stop: ");
			name = scanner.next();
			
			if (name.equalsIgnoreCase("q"))
			{
				break; 
			}
			
			else if (User.primaryProductSearch(name) != null)
			{
				System.out.println("Product name: $" + User.primaryProductSearch(name).getName());
				System.out.println("Product ID: $" + User.primaryProductSearch(name).getProductId());
				System.out.println("Product Price: $" + User.primaryProductSearch(name).getCost());
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
			id = scanner.next();
			
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
