package IO;

import ADT.BST;

public class OrderIO {
	private PriorityQueue<Order> orderslist; 
	// private String filename; 
	
	public CustomerIO(String fname)
	{
		super(fname);
		orderslist = new PriorityQueue<Order>();
	}
	
	public PriorityQueue<Order> readfile()
	{
		
	}
	
	public void writefile()
	{
		
	}
}
