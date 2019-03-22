package Tests;

import static org.junit.Assert.*;

import org.junit.Test;

import Modules.Customer;
import Modules.Order;
import Modules.Product;

public class ProductTestCases {

	@Test
	public void testForProductUnitPrice() {
		
		Product product = new Product("PC","AB123",440.0,10.0,"Dell","very nice");
		double expected = 100.0;
		assertEquals(product.getUnitPrice(),expected,0.0);
	}
	
	@Test
	public void testForProductName() {
		
		Product product = new Product("PC","AB123",440.0,10.0,"Dell","very nice");
		String  expected = "Cel_Phone";
		assertEquals(product.getName(),expected);
	}
	
	@Test
	public void testForProductManufacturer() {
		
		Product product = new Product("PC","AB123",440.0,10.0,"Dell","very nice");
		String  expected = "Dell";
		assertEquals(product.getManufacturer(),expected);
	}
	
	@Test
	public void testForProductDescription() {
		
		Product product = new Product("PC","AB123",440.0,10.0,"Dell","very nice");
		String  expected = "very nice";
		assertEquals(product.getDescription(),expected);
	}
	
	@Test
	public void testForProductId() {
		
		Product product = new Product("PC","AB123",440.0,10.0,"Dell","very nice");
		String  expected = "C100";
		assertEquals(product.getProductId(),expected);
	}

	@Test
	public void testForProductCost() {
		
		Product product = new Product("PC","AB123",440.0,10.0,"Dell","very nice");
		double expected = 400.0;
		assertEquals(product.getCost(),expected,0.0);
	}
	

	@Test
	public void testForDuplicatesProduct() {
		
		Product product = new Product("PC","AB123",440.0,10.0,"Dell","very nice");
		Product product2 = new Product("PC","AB123",440.0,10.0,"Dell","very nice");
		int expected = 0;
		assertEquals(product.compareTo(product2),expected);
	}
	
	
	
}
