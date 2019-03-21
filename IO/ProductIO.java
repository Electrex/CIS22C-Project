package IO;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

import ADT.*;
import Modules.Product;
import User.*; 
public class ProductIO {
	
	private BST productlist; 
	private String filename; 
	private Scanner scanner; 

	
	public ProductIO(String fname)
	{
		filename = fname; 
		productlist = new BST();
		scanner = new Scanner(System.in);
	}

	public ProductIO(String fname, BST list)
	{
		filename = fname; 
		productlist = list;
		scanner = new Scanner(System.in);
	}

	/********
	 * 
	 * @return a completed hash. 
	 */
	public BST readfile()
	{
		boolean readable = false;
		boolean doneLoadingGraph = false;
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
				// String name, String productId, double cost, double unitPrice, String manufacturer, String description
				productlist.insert(new Product(vertices[0], vertices[1], Double.parseDouble(vertices[2]), 
						Double.parseDouble(vertices[3]), vertices[4], vertices[5]));
			}
			buff.close();
		} catch (IOException e) {
			System.out.println("readfile(): Problem reading file. " + e.toString());
		}
		return productlist; 
	}
	
	/********
	* overwrite the entire file. 
	 * @throws IOException 
	*/
	public void rewritefile() throws IOException
	{
		boolean isinvalid = true;           
		FileWriter output = new FileWriter(filename);   
		PrintWriter filewriter = new PrintWriter(output); 
		
		for (int i = 0; i < User.getproducts().getProducts().size(); i ++)
			filewriter.write(User.getproducts().getProducts().get(i).toString()); 
			
		try {
			output.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
