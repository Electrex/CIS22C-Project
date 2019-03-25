package Launcher;
//Driver class for program

import java.io.File;
import java.util.Scanner;

import ADT.*;
import IO.OrderIO;
import Modules.*;
import UI.*; 
import User.*; 

public class DashDriver {
	
	private static void runner()
	{
    	String [] files = {"Customer.csv", 
    			"Employee.csv", 
    			"Orders.csv", 
    			"Products.csv"}; 
    	User user = new User(files);
    	user.loaddata();
    	Welcome initializer = new Welcome();
    	initializer.welcome();
    	
    	user.outputdata(); // exit
	}
	
	private static void debugger()
	{
    	String [] files = {"Customer.csv", 
    			"Employee.csv", 
    			"Orders.csv", 
    			"Products.csv"}; 
    	User user = new User(files);
    	user.loaddata();
    	user.outputdata();
    	
 
    	/*
    	for (int i = 0; i < User.getorders().get_size(); i++)
		{
			System.out.println(User.getorders().get_element(i));		
		}
		*/	
	}
	
    public static void main(String[] args) throws Exception{
        
        //debugger();
    	runner();
    	
    	
    	/*
        int numBuckets = 26; //Choose whatever
        
        //Hash<Product> p = new Hash<Product>(numBuckets);
        String userChoice = "";
        String name;
        String productID;
        double cost;
        double unitPrice;
        Scanner reader = new Scanner(System.in);
        File file = new File("data.txt"); // or .xml
        Scanner sc = new Scanner(file);

        while (sc.hasNextLine()){
            name = sc.nextLine();
            productID = sc.nextLine();
            cost = sc.nextDouble();
            sc.nextLine();
            unitPrice = sc.nextDouble();
            if (sc.hasNextLine()){
                sc.nextLine();
                sc.nextLine();
            }
            p.insert(new Product(unitPrice, name, productID, cost));
        }
        System.out.println("Welcome to Dash!\n");

        do {
            //Do stuff
        } while ();
        System.out.println("Goodbye!");
        */
    }
}
