package User;

import ADT.*;
import IO.*;
import Modules.*;

public class User {
	private static BST<Product> productlist; 
	private static PriorityQueue orderslist; 
	private static Hash<Customer> customerlist; 
	private static List<Employee> employeelist;
	private String[] filename;
	
	
	public User()
	{
		productlist = new BST<Product>();
		orderslist = new PriorityQueue();
		customerlist = new Hash<Customer>();
		employeelist = new List<Employee>();
	}
	
	public User(String[] Filename)
	{
		productlist = new BST<Product>();
		orderslist = new PriorityQueue();
		customerlist = new Hash<Customer>();
		employeelist = new List<Employee>();
		this.filename = Filename; 
	}
	
	/*******
	 * load all the data from the database to the computer memory
	 */
	public void loaddata()
	{
		CustomerIO cusio = new CustomerIO(filename[0]);
		EmployeeIO empio = new EmployeeIO(filename[1]);
		OrderIO ordio = new OrderIO(filename[2]);
		ProductIO proio = new ProductIO(filename[3]);
		
		customerlist = cusio.readfile();
		employeelist = empio.readfile();
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
		
		cusio.rewritefile();
		empio.rewritefile();
		proio.rewritefile();
		ordio.rewritefile();
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
				break;
			case "h":
			case "H":
				customerlist.insert((Customer)(data));
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
				// orderslist.remove(orderslist.);// TODO HOW does remove work in this
				break;
			case "h":
			case "H":
				customerlist.remove((Customer)(data));
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
	 * @return a list of products that contains the input in their primary key (name) 
	 */
	public static List<Product> primaryProductSearch(String input)
	{
		List<Product> list = new List<Product>();
		
		// TODO Yusuf: Sort the BST by primary key
		
		for (int i = 0; i < User.getproducts().getSize(); i ++)
		{
			if (User.getproducts().searchByPrimary(input)) // if it is found
			{
				list.addLast(User.getproducts().getproduct); // TODO Yusuf: need get product method 
			}
		}
		
		return list;
	}
	
	/*******
	 * Search for the product using the secondary key 
	 * @param input that the user has entered in. 
	 * @return a list of products that contains the input in their secondary key (ID) 
	 */
	public static List<Product> secondaryProductSearch(String input)
	{
		List<Product> list = new List<Product>();
		
		// TODO Yusuf: Sort the BST by secondary key
		
		for (int i = 0; i < User.getproducts().getSize(); i ++)
		{
			if (User.getproducts().searchBySecondary(input)) // if it is found
			{
				list.addLast(User.getproducts().search()); // TODO Yusuf: need get product method. 
			}
		}
		
		return list;
	}
	
	/*******
	 * This will display the productlist that is sorted by the primary key. 
	 * @postcondition The productlist will be sorted by the primary key. 
	 * @return The productlist that is being sorted by the primary key. 
	 */
	public static BST<Product> displayProductPrimarily()
	{
		// sort product by primary key
		User.setProductlist(User.getproducts()); // TODO Yusuf: Need to figure out a way to sort by primary key. 
												 // TODO Yusuf: need to figure out a way to return the BST
		
		return User.getproducts(); 
	}
	
	/*******
	 * This will display the productlist that is sorted by the secondary key. 
	 * @postcondition The productlist will be sorted by the secondary key. 
	 * @return The productlist that is being sorted by the secondary key. 
	 */
	public static BST<Product> displayProductSecondary()
	{
		// sort product by secondary key
		User.setProductlist(User.getproducts()); // TODO Yusuf: need to figure out a way to sort by secondary key
												 // TODO Yusuf: need to figure out a way to return the BST in arraylist format
				
		return User.getproducts(); 
	}
	
	
	/******
	 * getter for product list
	 * @return productlist
	 */
	public static BST<Product> getproducts() {

		return productlist;
	}
	
	public static void setProductlist(BST<Product> productlist) {
		User.productlist = productlist;
	}
	
	public static PriorityQueue getorders() {
		return orderslist;
	}
	
	public static void setOrderslist(PriorityQueue orderslist) {
 
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
