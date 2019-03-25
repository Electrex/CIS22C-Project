package IO;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

import ADT.*;
import Modules.*; 
import User.*; 
public class EmployeeIO {
	
	private String filename; 
	
	private List<Employee> employeelist; 
	Scanner scanner; 
	// private String filename; 
	
	public EmployeeIO(String fname)
	{
		filename = fname; 		
		scanner = new Scanner(System.in); 
		employeelist = new List<Employee>();
	}
	
	public EmployeeIO(String fname, List<Employee> list)
	{
		filename = fname; 
		employeelist = list;
		scanner = new Scanner(System.in);
	}

	/********
	 * 
	 * @return a completed List. 
	 */
	public List<Employee> readfile()
	{
		boolean readable = true;
		BufferedReader buff;
		FileReader filereader;

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
				
				String[] vertices = line.split(",");
				employeelist.addLast(new Employee(vertices[0], vertices[1], vertices[2]));
			}
			buff.close();
		} catch (IOException e) {
			System.out.println("readfile(): Problem reading file. " + e.toString());
		}
		return employeelist; 
	}
	
	/********
	* overwrite the entire file. 
	 * @throws IOException 
	*/
	public void rewritefile() throws IOException
	{
		FileWriter output = new FileWriter(filename);   
		PrintWriter filewriter = new PrintWriter(output); 
		
		User.getemployees().pointIterator();
		
		for(int i = 0; i < User.getemployees().getLength(); i++)
		{
		    filewriter.write(User.getemployees().getIterator().toString());
		    User.getemployees().advanceIterator();
		} 
			
		try {
			output.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
