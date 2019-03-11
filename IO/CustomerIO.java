package IO;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

import ADT.*;
import Modules.*;

public class CustomerIO{
	
	private static Hash<Customer> customerlist; 
	private String filename; 
	private File file;
	private Scanner scanner; 

	
	public CustomerIO(String fname)
	{
		filename = fname; 
		file = new File(filename); 
		customerlist = new Hash<Customer>();
		scanner = new Scanner(System.in);
	}
	
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
	
	public void rewritefile()
	{
		
	}
	
	public void Findstr() { // This function searches the text for the    string
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
}
