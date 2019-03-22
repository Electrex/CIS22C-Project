package Tests;

import static org.junit.Assert.*;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import Modules.*;
import org.junit.Test;

public class OrderTestCases {

	/*
	@Test
	public void testForCreateOrder() {
		
		Order order = new Order();
		int expected = 1;
		//System.out.println(order.getOrderID());
		assertEquals(order.getOrderID(),expected);
		Order order2 = new Order();
		expected = 2;
		//System.out.println(order2.getOrderID());
		assertEquals(order2.getOrderID(),expected);
		
	}
	*/
	
	@Test
	public void testForOrderDate() {
		
		Order order = new Order();
		//System.out.println(order.getOrderDate());
		Timestamp t = new Timestamp(System.currentTimeMillis());
		order.setOrderDate(t);
		SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy HH:mm:ss");
		String expected = sdf.format(t);
		assertEquals(order.getOrderDate(),expected);
	}
	
	
	@Test
	public void testForHowManyProductsInOrder() {
		
		Order order = new Order();
		ArrayList<Product> products = new ArrayList<>();
		products.add(new Product("PC Laptop","123", 200.0, 1000.0, "Lenovo", "Tech specs here"));
		order.setProduct(products);
		int expected = 1;
		System.out.println(order.getProduct());
		System.out.println(order.getProduct().size());
		assertEquals(order.getProduct().size(), expected);
	}
	
	
	@Test
	public void testForEmptyOrder() {
		
		Order order = new Order();
		ArrayList<Product> products = new ArrayList<>();
		order.setProduct(products);
		int expected =0;
		assertEquals(order.getProduct().size(),expected);
	}
	
	
	@Test
	public void testForCustomerName() {
		
		Order order = new Order();
		ArrayList<Product> products = new ArrayList<>();
		order.setCustomerName("Suzy Q");
		String expected = "Suzy Q";
		assertEquals(order.getCustomerName(),expected);
	}
	
	@Test
	public void testForOrderQuantity() {
		
		
		ArrayList<Product> products = new ArrayList<>();
		ArrayList<Integer> qty = new ArrayList<>();
		qty.add(3);
		products.add(new Product("PC Laptop","123", 200.0, 1000.0, "Lenovo", "Tech specs here"));
		
		Order order = new Order(products, qty, "Suzy Q", "Standard Shipping", false);
		int expected = 3;
		
		assertEquals(order.getQuantity().get(0).intValue(),expected);
	}

	/*
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
	
	*/
}
