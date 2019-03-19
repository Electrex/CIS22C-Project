package IO;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

import ADT.*;
import Modules.*;

public class CustomerIO {
	
	private Hash<Customer> customerlist; 
	private String filename; 
	private Scanner scanner; 

	
	public CustomerIO(String fname)
	{
		filename = fname; 
		customerlist = new Hash<Customer>(50);
		scanner = new Scanner(System.in);
	}
	
	public CustomerIO(String fname, Hash<Customer> list)
	{
		filename = fname; 
		customerlist = list;
		scanner = new Scanner(System.in);
	}
	
	/********
	 * 
	 * @return a completed hash. 
	 */
	public Hash<Customer> readfile()
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
				customerlist.insert(new Customer()); // @TODO To be inserted. 
			}
			buff.close();
		} catch (IOException e) {
			System.out.println("readfile(): Problem reading file. " + e.toString());
		}
		return customerlist; 
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
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}   
		
		PrintWriter filewriter = new PrintWriter(output); 
		
		filewriter.write(customerlist.toString()); 
		// @Todo Eugene write a tostring that matches the format people like. 
			
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
