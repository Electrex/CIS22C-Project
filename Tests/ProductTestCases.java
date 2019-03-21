package Tests;

import static org.junit.Assert.*;

import org.junit.Test;

import Modules.Customer;
import Modules.Order;
import Modules.Product;

public class ProductTestCases {

	@Test
	public void testForProductUnitPrice() {
		
		Product product = new Product(100.0,"Cell_Phone","C100",400.0);
		double expected = 100.0;
		assertEquals(product.getUnitPrice(),expected,0.0);
	}
	
	@Test
	public void testForProductName() {
		
		Product product = new Product(100.0,"Cell_Phone","C100",400.0);
		String  expected = "Cel_Phone";
		assertEquals(product.getName(),expected);
	}
	
	@Test
	public void testForProductId() {
		
		Product product = new Product(100.0,"Cell_Phone","C100",400.0);
		String  expected = "C100";
		assertEquals(product.getProductId(),expected);
	}

	@Test
	public void testForProductCost() {
		
		Product product = new Product(100.0,"Cell_Phone","C100",400.0);
		double expected = 400.0;
		assertEquals(product.getCost(),expected,0.0);
	}
	

	@Test
	public void testForDuplicatesProduct() {
		
		Product product = new Product(100.0,"Cell_Phone","C100",400.0);
		Product product2 = new Product(100.0,"Cell_Phone","C100",400.0);
		int expected = 0;
		assertEquals(product.compareTo(product2),expected);
	}
	
	
	
}
