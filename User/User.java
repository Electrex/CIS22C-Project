package User;

import java.io.IOException;
import java.util.ArrayList;

import ADT.*;
import IO.*;
import Modules.*;

public class User {
	private static BST productlist; 
	private static PriorityQueue<Order> orderslist; 
	private static Hash<Customer> customerlist; 
	private static List<Employee> employeelist;
	private String[] filename;
	
	public User()
	{
		productlist = new BST();
		orderslist = new PriorityQueue<Order>();
		customerlist = new Hash<Customer>(50);
		employeelist = new List<Employee>();
	}
	
	public User(String[] Filename)
	{
		productlist = new BST();
		orderslist = new PriorityQueue<Order>();
		customerlist = new Hash<Customer>(50);
		employeelist = new List<Employee>();
		this.filename = Filename; 
	}
	
	/*******
	 * load all the data from the database to the computer memory
	 */
	public void loaddata()
	{
		EmployeeIO empio = new EmployeeIO(filename[1]);
		CustomerIO cusio = new CustomerIO(filename[0]);
		ProductIO proio = new ProductIO(filename[3]);
		OrderIO ordio = new OrderIO(filename[2]);

		employeelist = empio.readfile();
		customerlist = cusio.readfile();
		productlist = proio.readfile();
		orderslist = ordio.readfile();
	}
	
	
	/*********
	 * Output all the data from the computer memory to the database  
	 */
	public void outputdata()
	{
		CustomerIO cusio = new CustomerIO(filename[0], customerlist);
		EmployeeIO empio = new EmployeeIO(filename[1], employeelist);
		OrderIO ordio = new OrderIO(filename[2], orderslist);
		ProductIO proio = new ProductIO(filename[3], productlist);
		
		ordio.rewritefile();
		empio.rewritefile();
		cusio.rewritefile();
		proio.rewritefile();
	}
	
	/******
	 * This method adds new data to the data structure that is specified in parameter datatype
	 * @param datatype the ADT that the data is going to be inserted in. 
	 * 			datatype = "b" or "B" add to BST
	 * 			datatype = "p" or "P" add to PriorityQueue
	 * 			datatype = "h" or "H" add to Hash
	 * 			datatype = "l" or "L" add to List
	 * @param data that is to be inserted. 
	 * @throws IllegalArgumentException if the datatype is not a valid String that is stated above
	 */
	public static void adddata(String datatype, Object data) throws IllegalArgumentException
	{
		switch(datatype)
		{
			case "b": 
			case "B":
				productlist.insert((Product)(data));
				break;
			case "p":
			case "P":
				orderslist.insert((Order)(data));
				System.out.println("Inserted");
				break;
			case "h":
			case "H":
				Customer temp = (Customer)(data);
				CustomerIO.addtocontent(temp.toString());
				customerlist.insert(temp);
				break;
			case "l":
			case "L":
				employeelist.addLast((Employee)(data));
				break; 
			default:
				throw new IllegalArgumentException("adddata: The datatype is invalid so cannot be processed. ");
		}
	}
	
	/******
	 * This method removes the data from the data structure that is specified in parameter datatype
	 * @param datatype the ADT that the data is going to be inserted in. 
	 * 			datatype = "b" or "B" remove from BST
	 * 			datatype = "p" or "P" remove from PriorityQueue
	 * 			datatype = "h" or "H" remove from Hash
	 * 			datatype = "l" or "L" remove from List
	 * @param data that is to be removed. 
	 * @throws IllegalArgumentException if the datatype is not a valid String that is stated above
	 */
	public static void removedata(String datatype, Object data) throws IllegalArgumentException
	{
		switch(datatype)
		{
			case "b": 
			case "B":
				productlist.remove((Product)(data));
				break;
			case "p":
			case "P":
				for (int i = 0; i < orderslist.get_size(); i++)
					if (orderslist.get_element(i).equals(data))
						orderslist.remove(i); 
				break;
			case "h":
			case "H":
				Customer temp = (Customer)(data);
				CustomerIO.removecontent(temp.toString());
				customerlist.remove(temp);
				break;
			case "l":
			case "L":
				employeelist.moveToIndex(employeelist.linearSearch((Employee)(data)));
				employeelist.removeIterator();
				break; 
			default:
				throw new IllegalArgumentException("removedata: The datatype is invalid so cannot be processed. ");
		}
	}
	
	/*******
	 * Search for the product using the primary key 
	 * @param input that the user has entered in. 
	 * @return the product that contains the input in its primary key (name) 
	 */
	public static Product primaryProductSearch(String input) // should be product name 
	{
		BST temp = new BST(productlist); // temp is here so the productlist itself is always sorted in the order it should be sorted. 
		temp.sortByPrimary();
				
		for(int i = 0; i < temp.getProducts().size(); i++)
		{
		    if(temp.getProducts().get(i).getName().toLowerCase().contains(input.toLowerCase())) 
		    {
		        return temp.getProducts().get(i);
		    }
		}
		
		return null;
		/*temp.getProducts().contains(input)
		if (temp.searchByPrimary(new Product(input, "", 0.0, 0.0, "", ""))) // if it can be found
			for (int i = 0; i < temp.getProducts().size(); i ++)
				if (temp.getProducts().get(i).getName().equals(input)) // if product name == the ID inputed.     
					return temp.getProducts().get(i);
		return null; 
		*/
	}
	
	/*******
	 * Search for the product using the secondary key 
	 * @param input that the user has entered in. 
	 * @return the product that contains the input in its secondary key (ID) 
	 */
	public static Product secondaryProductSearch(String input) // should be ID number 
	{
	    BST temp = new BST(productlist);
	    temp.sortBySecondary();

	    for (int i = 0; i < temp.getProducts().size(); i++)
	    {
	        if (temp.getProducts().get(i).getProductId().equals(input)) // if product ID == the ID inputed.  
	        {
	            return temp.getProducts().get(i);
	        }
	    }
	    return null; 
	}
	
	/*******
	 * This will display the productlist that is sorted by the primary key. 
	 * @postcondition The productlist will be sorted by the primary key. 
	 * @return the temporary BST will be returned to keep the original BST sorted in its original order 
	 */
	public static BST displayProductPrimary()
	{
		// sort product by primary key
		BST temp = new BST(productlist);
		temp.sortByPrimary();
		
		return temp; 
	}
	
	/*******
	 * This will display the productlist that is sorted by the secondary key. 
	 * @postcondition The productlist will be sorted by the secondary key. 
	 * @return The productlist that is being sorted by the secondary key. 
	 */
	public static BST displayProductSecondary()
	{
		// sort product by secondary key
		BST temp = new BST(productlist);
		temp.sortBySecondary();		
				
		return temp; 
	}
	
	
	/******
	 * getter for product list
	 * @return productlist
	 */
	public static BST getproducts() {

		return productlist;
	}
	
	public static void setProductlist(BST productlist) {
		User.productlist = productlist;
	}
	
	public static PriorityQueue<Order> getorders() {
		return orderslist;
	}
	
	public static void setOrderslist(PriorityQueue<Order> orderslist) {
 
		User.orderslist = orderslist;
	}
	
	public static Hash<Customer> getcustomers() {
		return customerlist;
	}
	
	public static void setCustomerlist(Hash<Customer> customerlist) {

		User.customerlist = customerlist;
	}
	
	public static List<Employee> getemployees() {
		return employeelist;
	}
	
	public static void setEmployeelist(List<Employee> employeelist) {
		User.employeelist = employeelist;
	} 	
}
