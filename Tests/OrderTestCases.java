package Tests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import Modules.*;
import org.junit.Test;
import Modules.Order;

public class OrderTestCases {

	@Test
	public void testForCreateOrder() {
		
		Order order = new Order();
		int expected = 1;
		assertEquals(order.getOrderID(),expected);
	}
	
	@Test
	public void testForOrderDate() {
		
		Order order = new Order();
		String expected = "any_date";
		assertEquals(order.getOrderDate(),expected);
	}
	
	@Test
	public void testForHowManyProductsInOrder() {
		
		Order order = new Order();
		ArrayList<Product> products = new ArrayList<>();
		products.add(new Product(12.1,"PC","123",440.0));
		int expected =1;
		assertEquals(order.getProduct().size(),expected);
	}
	
	@Test
	public void testForEmptyOrder() {
		
		Order order = new Order();
		ArrayList<Product> products = new ArrayList<>();
		int expected =0;
		assertEquals(order.getProduct().size(),expected);
	}
	
	
	@Test
	public void testForOrderQuantity() {
		
		Order order = new Order();
		ArrayList<Product> products = new ArrayList<>();
		ArrayList<Integer> qnty = new ArrayList<>();
		qnty.add(1);
		products.add(new Product(12.1,"PC","123",440.0));
		int expected =1;
		assertEquals(order.getProduct().size(),expected);
		assertEquals(qnty.size(),expected);
	}

	
	@Test
	public void testForOrderShipType() {

		ArrayList<Product> products = new ArrayList<>();
		ArrayList<Integer> qnty = new ArrayList<>();
		qnty.add(1);
		products.add(new Product(12.1,"PC","123",440.0));
		Order order = new Order(products, qnty, "Rush Shipping");
		String expected ="Rush Shipping";
		assertEquals(order.getShipmentType(),expected);

	}
	
	@Test
	public void testForOrderShipping() {

		ArrayList<Product> products = new ArrayList<>();
		ArrayList<Integer> qnty = new ArrayList<>();
		qnty.add(1);
		products.add(new Product(12.1,"PC","123",440.0));
		Order order = new Order(products, qnty, "Rush Shipping");
		boolean expected = false;
		assertEquals(order.getIsShipped(),expected);

	}
}
