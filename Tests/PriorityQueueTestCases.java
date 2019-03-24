package Tests;

import static org.junit.Assert.*;

import org.junit.Test;

import ADT.PriorityQueue;

public class PriorityQueueTestCases {
	
	
	@Test
	public void testSizeofPriorityQueue() {
		PriorityQueue<String> obj = new PriorityQueue<String>();
		int expected = 0;
		assertEquals(obj.get_size(),expected);
	}
	
	
	@Test
	public void testPriorityQueueInsertElements() {
		PriorityQueue<String> obj = new PriorityQueue<String>();
		obj.insert("tom");
		//System.out.println(obj.get_size());
		obj.insert("clark");
		//System.out.println(obj.get_size());
		obj.insert("jimmy");
		//System.out.println(obj.get_size());
		int expected = 3;
		assertEquals(obj.get_size(),expected);
		//System.out.println(obj);
		obj.displayArray();
	}
	
	@Test
	public void testPriorityQueueRemoveElements() {
		PriorityQueue<String> obj = new PriorityQueue<>();
		obj.insert("tom");
		obj.insert("clark");
		obj.insert("jimmy");
		obj.remove(1);
		int expected = 2;
		assertEquals(obj.get_size(),expected);
	}
	
	@Test
	public void testPriorityQueueFindMaximum() {
		PriorityQueue<String> obj = new PriorityQueue<>();
		obj.insert("tom");
		obj.insert("clark");
		obj.insert("jimmy");
		String expected = "tom";
		assertEquals(obj.get_max(),expected);
	}
	
	@Test
	public void testPriorityQueueFindLeftNodeElement() {
		PriorityQueue<String> obj = new PriorityQueue<>();
		obj.insert("tom");
		obj.insert("clark");
		obj.insert("jimmy");
		int expected = 2;
		//System.out.println(obj.get_left(1));
		//System.out.println(obj.get_right(1));
		assertEquals(obj.get_left(1),expected);
	}
	
	@Test
	public void testPriorityQueueFindRightNodeElement() {
		PriorityQueue<String> obj = new PriorityQueue<>();
		obj.insert("tom");
		obj.insert("clark");
		obj.insert("jimmy");
		int expected = 3;
		assertEquals(obj.get_right(1),expected);
	}
	
	@Test
	public void testPriorityQueueFindParentNodeElement() {
		PriorityQueue<String> obj = new PriorityQueue<>();
		obj.insert("tom");
		obj.insert("clark");
		obj.insert("jimmy");
		int expected = 1;
		assertEquals(obj.get_parent(3),expected);
	}
	

}
