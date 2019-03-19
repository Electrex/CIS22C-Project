package ADT;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import Modules.Order;

class PriorityQueueInsertTest {

	@Test
	void test() {
		Order o = new Order();
		o.swapShipmentType("Overnight Shipping");
		
		Order o2 = new Order();
		o2.swapShipmentType("Standard Shipping");
		
		Order o3 = new Order();
		o3.swapShipmentType("Rush Shipping");
		
		PriorityQueue q = new PriorityQueue();
		q.insert(o);
		assertEquals(1, q.get_size());
		
		q.insert(o2);
		assertEquals(2, q.get_size());
		
		q.insert(o3);
		assertEquals(o, q.get_max());
		System.out.println("Heap size is now: " + q.get_size());
		q.displayArray();
		q.sort();
		q.displayArray();
		
	}

}
