package IO;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Scanner;

import ADT.*;
import Modules.*;
import User.*;

public class OrderIO {
	
	private PriorityQueue<Order> orderslist; 
	private String filename; 
	private Scanner scanner; 
	private ArrayList<String[]> ordersfilecontent;
	private static final SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yy HH:mm"); 
	
	public OrderIO(String fname)
	{
		filename = fname; 
		scanner = new Scanner(System.in);
		ordersfilecontent = new ArrayList<String[]>();
		orderslist = new PriorityQueue<Order>();
	}
	
	public OrderIO(String fname, PriorityQueue<Order> list)
	{
		filename = fname; 
		scanner = new Scanner(System.in);
		ordersfilecontent = new ArrayList<String[]>();
		orderslist = list;
	}
	
	/********
	 * 
	 * @return a completed hash. 
	 */
	public PriorityQueue<Order> readfile()
	{
		boolean readable = true;
		BufferedReader buff;
		FileReader filereader;

		try {
			filereader = new FileReader(filename);
			buff = new BufferedReader(filereader);
			String line = "";

			//line = buff.readLine(); 
			
			while (readable) {

				line = buff.readLine();
				if (line == null) // finished reading
				{
					readable = false;
					break;
				}

				ordersfilecontent.add(line.split(","));
			}
			
			buff.close();
		} catch (IOException e) {
			System.out.println("readfile(): Problem reading file. " + e.toString());
		}
		
		ArrayList<Integer> orderID = new ArrayList<Integer>();
		ArrayList<Product> products = new ArrayList<Product>();
		ArrayList<Integer> quantities = new ArrayList<Integer>();
		
		for (int i = 0; i < ordersfilecontent.size(); i++)
		{
			orderID.add(Integer.parseInt(ordersfilecontent.get(i)[0]));
		}
		
		int lastorderid = orderID.get(0);
		boolean isshipped = Boolean.parseBoolean((ordersfilecontent.get(0)[1])); 
		String shiptype = ordersfilecontent.get(0)[4];
		String customer = ordersfilecontent.get(0)[5];
		String productid = ordersfilecontent.get(0)[6];
		Timestamp shipdate = null, orderdate = null; 
		try {
			orderdate = new Timestamp(sdf.parse(ordersfilecontent.get(0)[2]).getTime());
		} catch (ParseException e) {
			e.printStackTrace();
			orderdate = null;
		}
		try {
			shipdate = new Timestamp(sdf.parse(ordersfilecontent.get(0)[3]).getTime());
		}catch (ParseException e) {
			e.printStackTrace();
			shipdate = null;
		}

		products.add(User.secondaryProductSearch(productid));
		quantities.add(Integer.parseInt(ordersfilecontent.get(0)[7])); 
		
		for (int i = 1; i < orderID.size() - 1; i ++)
		{
			if (orderID.get(i) == orderID.get(i-1)) // still in same order 
			{
				products.add(User.secondaryProductSearch(ordersfilecontent.get(i)[6]));
				quantities.add(Integer.parseInt(ordersfilecontent.get(i)[6])); 
			}
			else // not in the same order
			{
				User.adddata("p", new Order(lastorderid, products, quantities, customer, orderdate, shipdate, shiptype, isshipped));
				products.clear();
				quantities.clear();
				lastorderid = orderID.get(i);
				isshipped = Boolean.parseBoolean((ordersfilecontent.get(i)[1])); 
				shiptype = ordersfilecontent.get(i)[4];
				customer = ordersfilecontent.get(i)[5];
				productid = ordersfilecontent.get(i)[6];
				try 
				{
					shipdate = new Timestamp(sdf.parse(ordersfilecontent.get(i)[3]).getTime());
				} catch (ParseException e) 
				{	shipdate = null;}//e.printStackTrace(); }
				
				try 
				{
					orderdate = new Timestamp(sdf.parse(ordersfilecontent.get(i)[2]).getTime());
				} catch (ParseException e) 
				{ orderdate = null;}//e.printStackTrace(); }
					
				products.add(User.secondaryProductSearch(productid));
				quantities.add(Integer.parseInt(ordersfilecontent.get(i)[7])); 
			}
		}

		return orderslist; 
	}
	
	/********
	* overwrite the entire file. 
	*/
	public void rewritefile()
	{
		boolean isinvalid = true;  
		FileWriter output = null;
		
		try
		{
			output = new FileWriter(filename);   
		}
		catch(IOException e) 
		{
			e.printStackTrace();
		}
		
		
		PrintWriter filewriter = new PrintWriter(output); 
		
		for (int i = 0; i < User.getorders().get_size(); i ++)
		{
			filewriter.write(User.getorders().get_element(i).toString());
			System.out.println(User.getorders().get_element(i).toString());
		}
			
		try {
			output.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (NullPointerException e) {
			e.printStackTrace();
		}
	}
	
}
