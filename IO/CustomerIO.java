package IO;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import ADT.*;
import Modules.*;
import User.*; 
public class CustomerIO {
	
	private Hash<Customer> customerlist; 
	private static ArrayList<String> customerlogininfo;
	private static ArrayList<String> customerfileinfo;
	private String filename; 
	private Scanner scanner; 

	
	public CustomerIO(String fname)
	{
		filename = fname; 
		customerlist = new Hash<Customer>(50);
		scanner = new Scanner(System.in);
		customerlogininfo = new ArrayList<String>();
		customerfileinfo = new ArrayList<String>();
	}
	
	public CustomerIO(String fname, Hash<Customer> list)
	{
		filename = fname; 
		customerlist = list;
		scanner = new Scanner(System.in);
		customerlogininfo = new ArrayList<String>();
		customerfileinfo = new ArrayList<String>();
	}
	
	/********
	 * 
	 * @return a completed hash. 
	 */
	public Hash<Customer> readfile()
	{
		boolean readable = true;
		boolean doneLoadingGraph = false;
		BufferedReader buff;
		FileReader filereader;
		Customer custemp; 

		try {
			filereader = new FileReader(filename);
			buff = new BufferedReader(filereader);
			String line;
			
			while (readable) {
				line = buff.readLine();
				if (line == null) // finished reading
				{
					readable = false;
					break;
				}
				// split line at space to break apart vertices u & v
				String[] vertices = line.split(",");
				custemp = new Customer(vertices[0], vertices[1], vertices[2], vertices[3], vertices[4], vertices[5]);
				customerlist.insert(custemp); 
				customerlogininfo.add(vertices[3] + "," + vertices[4]);
				customerfileinfo.add(line);
								
				for (int i = 1; i < User.getorders().get_size() + 1; i ++)
				{
					if (User.getorders().get_element(i).getCustomerName().equals(custemp.getFullName())) // the order belongs to the customer
					{
						custemp.insertOrder(User.getorders().get_element(i));
						//System.out.println(User.getorders().get_element(i).getCustomerName() + " == " + custemp.getFullName());
					}
					//else
						//System.out.println(User.getorders().get_element(i).getCustomerName() + " != " + custemp.getFullName());
				}
			}
			buff.close();
		} catch (IOException e) {
			System.out.println("readfile(): Problem reading file. " + e.toString());
		}
		
		return customerlist; 
	}
	
	
	
	public static ArrayList<String> getcustomerlogininfo()
	{
	    return customerlogininfo;
	}
	
	public static ArrayList<String> getcustomerfilecontent()
	{
		return customerfileinfo;
	}
	
	public static void addtocontent(String s)
	{
		customerfileinfo.add(s);
	}
	
	public static void removecontent(String s)
	{
		customerfileinfo.remove(customerfileinfo.indexOf(s));
	}
	
	/********
	* overwrite the entire file. 
	*/
	public void rewritefile()
	{
		boolean isinvalid = true;           
		FileWriter output = null;
		try {
			output = new FileWriter(filename);
			//output = new FileWriter("customeroutput.csv");
		} catch (IOException e1) {
			e1.printStackTrace();
		}   
		
		PrintWriter filewriter = new PrintWriter(output); 
		
		filewriter.write(User.getcustomers().toString()); 
		
		//System.out.println("\n\nCustomer");
		//System.out.print(User.getcustomers().toString());

		
		try {
			output.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/*
	 public void Findstr() { // This function searches the text 
		Scanner kb = new Scanner(System.in);
		System.out.println(" enter the content you looking for");
		String name = kb.next();
		Scanner scanner;
		try {
			scanner = new Scanner(file).useDelimiter( ",");

			while (scanner.hasNext()) {
				final String lineFromFile = scanner.nextLine();
				if (lineFromFile.contains(name)) {
		                // a match!
					System.out.println("I found " + name);
					break;
		        }
		    }
		}	 
		catch (IOException e) {
		        System.out.println(" cannot write to file " + file.toString());
		}
	}
	 */
}