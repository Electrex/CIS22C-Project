package IO;

import ADT.*; 
import Modules.*;


/***********
 * The filename will follow the following format: filename.txt
 * The content of the file will hold one object with its properties separated by a comma(,) per line. 
 * @author albertliu
 *
 */
public class SettleIO {
	private String filename;
	
	private BST<Product> productlist; 
	private PriorityQueue orderslist; 
	private Hash<Customer> customerlist; 
	private List<Employee> employeelist; 
	
	public SettleIO(String fname)
	{
		productlist = new BST<Product>();
		orderslist = new PriorityQueue();
		customerlist = new Hash<Customer>(50);
		employeelist = new List<Employee>();
	}	
	
	/********
	 * This method catches and handles all the exception
	 */
	public void handleException()
	{
		
	}
	
	
	
}
