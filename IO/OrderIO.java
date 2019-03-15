package IO;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

import ADT.*;
import Modules.*;

public class OrderIO {
	
	private PriorityQueue orderslist; 
	private String filename; 
	private Scanner scanner; 

	
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
				// split line at space to break apart vertices u & v
				String[] vertices = line.split(",");
				orderslist.insert(new Order(new Product(/*Search By Product ID*/), vertices[0], vertices[1])); // @TODO To be inserted. 
			}
			buff.close();
		} catch (IOException e) {
			System.out.println("readfile(): Problem reading file. " + e.toString());
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
		
		filewriter.write(orderslist.toString()); 
		// @Todo Eugene write a tostring that matches the format people like. 
			
		try {
			output.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (NullPointerException e) {
			e.printStackTrace();
		}
	}
	
}
