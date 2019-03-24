package Tests;

import static org.junit.Assert.*;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import Modules.*;
import org.junit.Test;


public class OrderTestCases {

	
	@Test
	public void testForCreateOrder() {
		
		Order order = new Order(1, null, null, "Mia", null, null, "Rush", false);
		int expected = 1;
		//System.out.println(order.getOrderID());
		assertEquals(order.getOrderID(),expected);
		Order order2 =new Order(2, null, null, "Mia", null, null, "Rush", false);
		expected = 2;
		//System.out.println(order2.getOrderID());
		assertEquals(order2.getOrderID(),expected);
		
	}
	
	
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
		
		Order order = new Order(products, qty, "Suzy Q", "Standard");
		int expected = 3;
		
		assertEquals(order.getQuantity().get(0).intValue(),expected);
	}

	@Test
	public void testForOrderCompareTo() {
		
		Order order = new Order(null, null, "", "Standard");
		Order order2 = new Order(null, null, "", "Overnight");
		
		Order order3 = new Order(null, null, "", "Standard");
		Timestamp t = new Timestamp(System.currentTimeMillis());
		order.setOrderDate(t);
		order3.setOrderDate(t);
		
		//order is lower priority than order2
		assertEquals(order.compareTo(order2), -1);
		
		//order2 is higher priority than order 
		assertEquals(order2.compareTo(order), 1);
		
		//same priority
		assertEquals(order3.compareTo(order), 0);
	}

	
	@Test
	public void testForOrderShipType() {

		ArrayList<Product> products = new ArrayList<>();
		ArrayList<Integer> qnty = new ArrayList<>();
		qnty.add(1);
		products.add(new Product("PC","AB123",440.0,10.0,"Dell","very nice"));
		Order order = new Order(products, qnty, "Clark","Rush");
		String expected ="Rush";
		assertEquals(order.getShipmentType(),expected);

	}
	
	
	@Test
	public void testForOrderShipping() {

		ArrayList<Product> products = new ArrayList<>();
		ArrayList<Integer> qnty = new ArrayList<>();
		qnty.add(1);
		products.add(new Product("PC","AB123",440.0,10.0,"Dell","very nice"));
		Order order = new Order(products, qnty, "Tom","Rush");
		order.toggleIsShipped();
		boolean expected = true;
		assertEquals(order.getIsShipped(), expected);

	}
	
	@Test
	public void testForTotalOrderPrice() {

		ArrayList<Product> products = new ArrayList<>();
		ArrayList<Integer> qnty = new ArrayList<>();
		qnty.add(1);
		qnty.add(1);
		products.add(new Product("PC","AB123",50.0,500.0,"Dell","kinda nice"));
		products.add(new Product("Macbook Pro","AB123",150.0,1500.0,"Mac","extra nice"));
		Order order = new Order(products, qnty, "Tom","Rush");
		double expected = 2000.0;
		//System.out.println(order.totalOrderPrice());
		assertEquals((int) order.totalOrderPrice(),(int) expected);

	}
	
	/*
	@Test
	public void testToReadOrdersFromFile() {
		OrderIO orders = new OrderIO("Orders.csv");
		PriorityQueue<Order> ordersList = orders.readfile();
		boolean expected = true;
		assertEquals(ordersList.get_size() > 0, expected);
	}
	*/
	
}
