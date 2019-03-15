package ADT;

import java.util.ArrayList;

/**
 * Heap ADT that prioritizes Orders into a priority queue
 * @author Mia Skinner
 *
 */
public class PriorityQueue {
	
	private int heap_size;
	private ArrayList<Order> heap;
	
	public PriorityQueue(){
		heap_size = 0;
		heap = new ArrayList<Order>();
	}

	/**
	 * Given a valid max heap (except for a single node i), heapify processes the 
	 * tree with the max-heapify algorithm to rearrange node i and its 
	 * descendants to satisfy the max-heap property.
	 * @precondition Given a valid max heap (except for a single node)
	 * @postcondition A is valid max heap
	 * @param A, a tree which is already a valid Max Heap (except for node i)
	 * @param i
	 */
	private void heapify(ArrayList<Integer> A, int i){
		
		int index_of_max = i;
		int left = (int) i * 2; //get the index of the left child of A[i] and store as l
		int right = (int) (i * 2) + 1; //get the index of the right child of A[i] and store r

	
		//Check if l is off the end of the array (heap) AND compare A[i] to its left child
		if (left <= heap_size(A) && A.get(left) > A.get(i)) {
	
		    index_of_max = left; //update index_of_max if left is bigger
		}
	
		//Check if r is off the end of the array (heap) AND compare A[r] to current max value
	
		if (right <= heap_size(A) && A.get(right) > A.get(index_of_max)) {
	
			index_of_max = right; //update index_of_max if right is bigger
		}
	
		if (i != index_of_max) {//if A[i] was not bigger than its two children
			int oldMax = A.get(index_of_max);
			int newMax = A.get(i);
		    A.set(index_of_max, newMax); //swap, so now A[i] stored at A[index_of_max]
		    A.set(i, oldMax);
	
		    heapify(A, index_of_max); //recursively move through tree until restore heap property
		}
	}
	
	private void heapIncreaseKey(int i, int key) {
		//TODO heapIncreaseKey
	}
	
	public void buildHeap(ArrayList<Integer> A) {

		int n = getSize();

		for (int i = getSize(A)/2; i >= 1; i--) {//start at floor(n/2); we can ignore leaf nodes

			System.out.printf("Calling MaxH with %d as i\n", i);
			heapify(A, i); //call heapify helper function
		}
		
	}
	
	public void insert(Order order) {
		//TODO insert
	}
	
	public void remove(int index) {
		//TODO remove
	}
	
	public ArrayList<Order> sort(){
		//TODO sort
		
		return ArrayList<Order>;
	}
	
	public int getMax() {
		//TODO get_max
		
		//root heap[1]
		return int;
	}
	
	public int getParent(int i) {
		//TODO getParent
		
		// A[i] = A[floor(i/2)]
		return int;
	}
	
	public int getLeft(int i) {
		//TODO getLeft
		//A[2i]
		return int;
	}
	
	public int getRight(int i) {
		//TODO getRight
		//A[2i + 1]
		return int;
	}
	

	public int heapSize() {
		// TODO size
		return heap.size() - 1;
	}
	
	public int getElement(int i) {
		//TODO getElement
		return heap.get(i);
	}

	
	@Override
	public String toString() {
		//TODO toString
		return "PriorityQueue [sort()=" + sort() + ", get_max()=" + get_max() + ", size()=" + size() + "]";
	}
	
	
	public void displayArray() {
		//TODO displayArray
	}

	
	
	
}


