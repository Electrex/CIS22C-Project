package UI;

import java.util.Scanner;

import ADT.BST;
import User.*;


/*******

Log in:     
- Log in as customer
- Create a new account (added to the file)
- Log in as a guest
- Log in as an Employee

For Customer User: 
- Search for a product
	- Find and display one record using the primary key
	- Find and display one record using the secondary key
- List Database of Products
	- List data sorted by primary key
	- List data sorted by secondary key
- Place an Order
	- Overnight Shipping 
	- Rush Shipping
	- Standard Shipping
- View Purchases
	- View shipped orders
	- View unshipped orders
- Quit

For employee user:
- Search for a customer by name
- Display unsorted customer information, including first and last name, address, phone number, order history
- View Orders by Priority
- Ship an Order
- List Database of Products
	- List data sorted by primary key
	- List data sorted by secondary key
- Add a new product
- Remove a product
- Quit

 * @author albertliu
 */
public class Welcome {

    private Scanner scanner; 
    private static Client client;
    private static Server server;
    private boolean exit;

    public Welcome()
    {
        scanner = new Scanner(System.in); 
        client = new Client();
        server = new Server();
        exit = false;
    }

    public static Client getClient()
    {
        return client; 
    }

    public static Server getServer()
    {
        return server; 
    }

    public void welcome()
    {
        System.out.println("********************* Welcome to Dash! *********************");
        start();
    }

    public void start()
    {
        while(exit == false)
            if (client.isloggedin())
                loggedinmenus(); 
            else if (server.isloggedin())
                employeeloggedinmenus();
            else 
                guestmenus(); 
    }

    public void guestmenus()
    {
        String input = ""; boolean isvalid = false;
        System.out.println("\nWelcome Guest!\n"); 
        System.out.println("Menu Options");
        System.out.println("------------");
        System.out.println("S: Search for a product");            
        System.out.println("L: Log In or Create an Account ");   
        System.out.println("Q: Quit ");  
        System.out.print("\nPlease choose one of the options: ");

        input = scanner.nextLine();

        while(true)
        {
            if(!input.equalsIgnoreCase("S") && !input.equalsIgnoreCase("L") && !input.equalsIgnoreCase("Q"))
            {
                System.out.println("----Invalid input! Please enter again.\n");
                System.out.println("Menu Options");
                System.out.println("------------");
                System.out.println("S: Search for a product");            
                System.out.println("L: Log In or Create an Account ");   
                System.out.println("Q: Quit ");  
                System.out.print("\n****Please choose one of the options: ");
                input = scanner.nextLine();
            }
            else
            {
                break;
            }
        }

        while (!isvalid)
        {
            if (input.equalsIgnoreCase("s"))
            {	
                isvalid = true;
                new SearchPage().Search();
            }
            else if (input.equalsIgnoreCase("l"))
            {
                isvalid = true;
                new LogIn().logIn();
            }
            else if (input.equalsIgnoreCase("q"))
            {
                isvalid = true;
                exit = true; 
                System.out.println("Goodbye!");
                break;
            }
            else
            {
                System.out.println("\nInvalid input!");
                System.out.print("Please choose one of the options: ");
                input = scanner.nextLine();
            }
        }
    }

    public void loggedinmenus()
    {
        String input = ""; boolean isvalid = false; 
        System.out.println("\nWelcome customer, " + client.getcustomer().getFullName()); 
        System.out.println("\nMenu Options");
        System.out.println("------------");    
        System.out.println("S: Search for a product");     
        System.out.println("L: List Products ");
        System.out.println("V: View my shopping cart");  
        System.out.println("O: View my orders");   
        System.out.println("Q: Log out");
        System.out.print("\nPlease choose one of the options: "); 

        input = scanner.nextLine();

        while(true)
        {
            if(!input.equalsIgnoreCase("S") && !input.equalsIgnoreCase("L") && !input.equalsIgnoreCase("V") && !input.equalsIgnoreCase("O") && !input.equalsIgnoreCase("Q"))
            {
                System.out.println("Invalid input! Please enter again.\n");
                System.out.println("Menu Options");
                System.out.println("------------");    
                System.out.println("S: Search for a product");     
                System.out.println("L: List Products ");
                System.out.println("V: View my shopping cart");  
                System.out.println("O: View my orders");   
                System.out.println("Q: Log out");
                System.out.print("\nPlease choose one of the options: "); 

                input = scanner.nextLine();
            }
            else
            {
                break;
            }
        }

        while (!isvalid)
        {
            if (input.equalsIgnoreCase("S"))
            {	
                isvalid = true;
                new SearchPage().Search();
            }
            else if (input.equalsIgnoreCase("V"))
            {
                new ShoppingCart().displayshoppingcart();
                isvalid = true;
            }
            else if (input.equalsIgnoreCase("Q"))
            {
                isvalid = true;
                client.logout();
                System.out.println("You have successfully logged out.");
            }
            else if (input.equalsIgnoreCase("O"))
            {
                new ShoppingCart().viewOrder();
                isvalid = true;
            }
            else 
            {
                String choice;
                System.out.print("\nPress (N) to list all products by name or (I) to list all products by ID: ");
                choice = scanner.nextLine();

                while(true)
                {
                    if(!choice.equalsIgnoreCase("n") && !choice.equalsIgnoreCase("i"))
                    {
                        System.out.println("Invalid input! Please enter again.");
                        System.out.print("\nPress (N) to list all products by name or (I) to list all products by ID: ");
                        choice = scanner.nextLine();
                    }
                    else
                    {
                        break;
                    }
                }

                if (choice.equalsIgnoreCase("n"))
                {
                    System.out.println();
                    BST temp = User.displayProductPrimary(); 
                    for (int i = 0; i < temp.getProducts().size(); i++)
                    {
                        System.out.println("Product Name: " + temp.getProducts().get(i).getName());
                        System.out.println("Product Description: " + temp.getProducts().get(i).getDescription());
                        System.out.println("Product ID: " + temp.getProducts().get(i).getProductId());
                        System.out.printf("Product Price: $%.2f\n" , temp.getProducts().get(i).getUnitPrice());
                        //System.out.printf("Product Cost: $%.2d\n", temp.getProducts().get(i).getCost());
                        System.out.println("--------------------------------------------------------------");
                    }
                }
                else
                {
                    System.out.println();
                    BST temp = User.displayProductSecondary(); 
                    for (int i = 0; i < temp.getProducts().size(); i++)
                    {
                        System.out.println("Product ID: " + temp.getProducts().get(i).getProductId());
                        System.out.println("Product Name: " + temp.getProducts().get(i).getName());
                        System.out.println("Product Description: " + temp.getProducts().get(i).getDescription());
                        System.out.printf("Product Price: $%.2f\n" , temp.getProducts().get(i).getUnitPrice());
                        //System.out.println("Product Cost: " + temp.getProducts().get(i).getCost());
                        System.out.println("--------------------------------------------------------------");
                    }		
                }
                isvalid = true; 
            }
        }
    }

    public void employeeloggedinmenus()
    {
        String input = ""; boolean isvalid = false; 
        System.out.println("\nWelcome employee, " + server.getemployee().getFullname()); 
        System.out.println("\nMenu Options");
        System.out.println("------------");      
        System.out.println("S: Search for a product");  
        System.out.println("A: Add a product");  
        System.out.println("L: List out products");  
        System.out.println("C: Search for a customer");  
        System.out.println("V: View orders by priority");
        System.out.println("D: Ship an order");
        System.out.println("Q: Log out");
        System.out.print("\nPlease choose one of the option from the following: ");  

        EmployeeMenus menu = new EmployeeMenus();
        input = scanner.nextLine();
        while (!isvalid)
        {
            if (input.equalsIgnoreCase("S"))
            {	
                menu.searchproduct();
                isvalid = true;
            }
            else if (input.equalsIgnoreCase("A"))
            {	
                menu.addproduct();
                isvalid = true;
            }
            else if (input.equalsIgnoreCase("C"))
            {
                menu.searchcustomer();
                isvalid = true;
            }
            else if (input.equalsIgnoreCase("D"))
            {
                isvalid = true;
                menu.deliverorder();
            }	
            else if (input.equalsIgnoreCase("V"))
            {
                isvalid = true;
                menu.listorders();
            }	
            else if (input.equalsIgnoreCase("L"))
            {
                menu.listproduct();
                isvalid = true;
            }
            else if (input.equalsIgnoreCase("Q"))
            {
                server.logout();
                System.out.println("You have successfully logged out.");
                isvalid = true;
            }
            else
            {
                System.out.println("----Sorry, invalid output. Try again. ");
                input = scanner.nextLine();
                //continue; 
            }
        }
    }
}
