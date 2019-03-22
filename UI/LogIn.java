package UI;

import java.util.Scanner;

import User.*;
import Modules.*; 

public class LogIn {
	
	private Scanner scanner = new Scanner(System.in);
	
	public void logIn()
	{
		String uname, pass, ls; // log in or sign up 
		boolean isvalid = false; 
		
		System.out.println("Press (1) if you need to sign up, or (2) if you want to log in.");
		
		ls = scanner.nextLine();
		if (ls.equals("1"))
		{
			System.out.println("**********Sign Up**********");
			SignUp();
		}
		else if (ls.equals("2"))
		{
			System.out.println("**********Log In***********");
		}
		else
		{
			System.out.println("Invalid input. We're assuming you want to sign in...");
		
		}
	
		
		while (!isvalid)
		{
			System.out.print("Enter your username: ");
			uname = scanner.nextLine();
			System.out.print("Enter your password: ");
			pass = scanner.nextLine();
			
			if (Client.verifyLogInInformation(uname, pass) || Server.verifyLogInInformation(uname, pass))
			{
				if (Client.verifyLogInInformation(uname, pass))
				{
					System.out.println("Welcome to Customer Mode!"); 
					Welcome.getClient().login(uname, pass);
					isvalid = true;
				}
				else
				{
					System.out.println("Welcome to Employee Mode!"); 
					Welcome.getServer().login(uname, pass);
					isvalid = true;
				}
			}
			else
			{
				System.out.println("Invalid password, please retry! ");
				continue; 
			}
		}
	}
	
	public void SignUp()
	{
		String firstname, lastname, address, username, password, passwordconfirmation; 
		System.out.print("First Name: " );
		firstname = scanner.nextLine();
		System.out.print("Last Name/Family Name: ");
		lastname = scanner.nextLine();
		System.out.print("Address: ");
		address = scanner.nextLine();
		System.out.print("Username: ");
		username = scanner.nextLine();
		System.out.print("Password: ");
		password = scanner.nextLine();
		System.out.print("Confirm Password: ");
		passwordconfirmation = scanner.nextLine();
		
		boolean isvalid = false;
		while(!isvalid)
		{
			if (password.equals(passwordconfirmation))
			{
				System.out.println("You are all set! ");
				Client.createnewaccount(firstname, lastname, username, password, address);
				isvalid = true;  
			}
			else
			{
				System.out.println("Passwords do not match, please re-enter your password. ");
				passwordconfirmation = scanner.nextLine();   
			}
		}
	}
}
