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
		boolean successlogin = false;
		
		while (!successlogin)
		{
			System.out.println("Press (1) if you need to sign up, or (2) if you want to log in, Q to go back to homepage.");
			
			ls = scanner.nextLine();
			if (ls.equals("1"))
			{
				System.out.println("**********Sign Up**********");
				SignUp();
				successlogin = true;      
			}
			else if (ls.equals("2"))
			{
				System.out.println("\n\n**********Log In***********");
				while (!isvalid)
				{
					System.out.print("**** Enter your username or press Q to go back: ");
					uname = scanner.nextLine();
					if (uname.equalsIgnoreCase("q"))
						break; 
					System.out.print("**** Enter your password press Q to go back: ");
					pass = scanner.nextLine();
					if (pass.equalsIgnoreCase("q"))
						break; 
					
					if (Client.verifyLogInInformation(uname, pass) || Server.verifyLogInInformation(uname, pass))
					{
						if (Client.verifyLogInInformation(uname, pass))
						{
							Welcome.getClient().login(uname, pass);
							isvalid = true;
						}
						else
						{
							Welcome.getServer().login(uname, pass);
							isvalid = true;
						}
						successlogin = true;      
					}
					else
					{
						System.out.println("---- Invalid password, please retry! ");
						continue; 
					}
				}
			}
			else if (ls.equalsIgnoreCase("q"))
			{
				break; 
			}
			else
			{
				System.out.println("---- Invalid input. Please retry . . . ");
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
				System.out.println(" \nYou are all set! \n\n");
				Client.createnewaccount(firstname, lastname, username, password, address);
				isvalid = true;  
			}
			else
			{
				System.out.println("---- Passwords do not match, please re-enter your password. ");
				passwordconfirmation = scanner.nextLine();   
			}
		}
	}
}
