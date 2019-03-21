package IO;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import ADT.*;
import Modules.*;
import User.*;

public class OrderIO {
	
	private PriorityQueue orderslist; 
	private String filename; 
	private Scanner scanner; 
	private ArrayList<String> ordersfilecontent;

	
	public OrderIO(String fname)
	{
		filename = fname; 
		scanner = new Scanner(System.in);

		orderslist = new PriorityQueue();
	}
	
	public OrderIO(String fname, PriorityQueue list)
	{
		filename = fname; 
		scanner = new Scanner(System.in);

		orderslist = list;
	}

	
	/********
	 * 
	 * @return a completed hash. 
	 */
	public PriorityQueue readfile()
	{
		boolean readable = false;
		boolean doneLoadingGraph = false;
		BufferedReader buff;
		FileReader filereader;

		try {
			filereader = new FileReader(filename);
			buff = new BufferedReader(filereader);
			String line;

			//line = buff.readLine(); 
			
			while (readable) {
				line = buff.readLine();
				if (line == null) // finished reading
				{
					readable = false;
					break;
				}
				ordersfilecontent.add(line);
			}
			buff.close();
		} catch (IOException e) {
			System.out.println("readfile(): Problem reading file. " + e.toString());
		}
		
		ArrayList<Product> products = new ArrayList<Product>();
		ArrayList<Integer> quantity = new ArrayList<Integer>(); 
		
		// Handle the first element
		String[] property = ordersfilecontent.get(0).split(",");
		String prevshipmenttype = property[3];
		String prevcustname = property[4];
		products.add(User.secondaryProductSearch(property[5]));
		quantity.add(Integer.parseInt(property[6]));
		boolean isshipped = false;
		if (property[0].equals("TRUE"))
			isshipped = true;
		else
			isshipped = false; 
		
		// Handle the rest of elements
		for (int i = 1; i < ordersfilecontent.size(); i ++)
		{
			property = ordersfilecontent.get(i).split(",");
			if (ordersfilecontent.get(i-1).contains(property[4]) && ordersfilecontent.get(i-1).contains(property[3])) 
			{ 			// under the same name & same ship mode
				products.add(User.secondaryProductSearch(property[5]));
				quantity.add(Integer.parseInt(property[6]));
				if (property[0].equals("TRUE"))
					isshipped = true;
				else
					isshipped = false; 
			}
			else // this belongs to a different order
			{
				orderslist.insert(new Order(products, quantity, prevcustname, prevshipmenttype, isshipped));  
				prevshipmenttype = property[3];
				prevcustname = property[4];

				products.clear();
				quantity.clear();
				
				products.add(User.secondaryProductSearch(property[5]));
				quantity.add(Integer.parseInt(property[6]));
				if (property[0].equals("TRUE"))
					isshipped = true;
				else
					isshipped = false; 
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
