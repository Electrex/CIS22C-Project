package User;

import IO.*;

public class Server {
	
	private SettleIO cusio, empio, ordio, proio;
		
	public Server ()
	{	
		cusio = new CustomerIO(); 
		empio = new EmployeeIO(); 
		ordio = new OrderIO(); 
		proio = new ProductIO(); 
	}
	
	public static void loadfile()
	{
		
	}
}
