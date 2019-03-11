package IO;

public class EmployeeIO {
	
	private String filename; 
	
	private PriorityQueue<Order> orderslist; 
	// private String filename; 
	
	public EmployeeIO(String fname)
	{
		super(fname);
		
		orderslist = new PriorityQueue<Order>();
	}
	
	public Hash<Employee> readfile()
	{
		
	}
	
	public void writefile()
	{
		
	}
}
