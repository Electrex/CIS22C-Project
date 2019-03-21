package Launcher;
//Driver class for program

import java.io.File;
import java.util.Scanner;

import Modules.Product;
import UI.*; 
import User.*; 

public class DashDriver {
    public static void main(String[] args) throws Exception{
        
    	String [] files = {"/Users/albertliu/Desktop/DataStruct/CIS22C-Project/Customer.txt", 
    			"/Users/albertliu/Desktop/DataStruct/CIS22C-Project/Employee.txt", 
    			"/Users/albertliu/Desktop/DataStruct/CIS22C-Project/Order.txt", 
    			"/Users/albertliu/Desktop/DataStruct/CIS22C-Project/Product.txt"}; 
    	User user = new User(files);
    	user.loaddata();
    	Welcome initializer = new Welcome();
    	initializer.welcome();
    	user.outputdata();
    	
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
