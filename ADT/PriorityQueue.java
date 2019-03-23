package ADT;

import java.util.ArrayList;


/**
 * Heap ADT that prioritizes T into a priority queue
 * @author Mia Skinner
 *
 */
public class PriorityQueue<T extends Comparable<T>>  {
	
	private int heap_size;
	private ArrayList<T> heap;
	
	/**
	 * Given a valid max heap (except for a single node i), heapify processes the 
	 * tree with the max-heapify algorithm to rearrange node i and its 
	 * descendants to satisfy the max-heap property.
	 * @precondition Given a valid max heap (except for a single node)
	 * @postcondition A is valid max heap
	 * @param A, a tree which is already a valid Max Heap (except for node i)
	 * @param i
	 */
	private void heapify(int i){
		
		int index_of_max = i;
		int left = get_left(i); //get the index of the left child of A[i] and store as l
		int right = get_right(i); //get the index of the right child of A[i] and store r

	
		//Check if l is off the end of the array (heap) AND compare A[i] to its left child
		if (left <= get_size() && heap.get(left).compareTo(heap.get(i)) > 0) {
	
		    index_of_max = left; //update index_of_max if left is bigger
		}
	
		//Check if r is off the end of the array (heap) AND compare A[r] to current max value
	
		if (right <= get_size() && heap.get(right).compareTo(heap.get(index_of_max)) > 0) {
	
			index_of_max = right; //update index_of_max if right is bigger
		}
	
		if (i != index_of_max) {//if A[i] was not bigger than its two children
			T oldMax = heap.get(index_of_max);
			T newMax = heap.get(i);
		    heap.set(index_of_max, newMax); //swap, so now A[i] stored at A[index_of_max]
		    heap.set(i, oldMax);
	
		    heapify(index_of_max); //recursively move through tree until restore heap property
		}
	}
	
	private void heapIncreaseKey(int i, T key) {
	    if(key.compareTo(heap.get(i)) > 0) {

	        heap.set(i, key); //write over existing value at i with key

		    while (i > 1 && heap.get(get_parent(i)).compareTo(heap.get(i)) < 0) {
	
		    	//while the parent is smaller and you are not at the root node
	
		    	//Swap parent and child
		    	T parent = heap.get(get_parent(i));
		    	heap.set(get_parent(i), heap.get(i));
		    	heap.set(i, parent);

		       i = get_parent(i); //keep track of current index of the key
		    }
	    }
	}
	
	/**Constructors*/
	
	public PriorityQueue(){
		heap_size = 0;
		heap = new ArrayList<T>();
		heap.add(null); // fill in placeholder for index zero
	}

	/**Mutators*/
	
	public void build_heap() {

		int n = get_size();

		for (int i = get_size()/2; i >= 1; i--) {//start at floor(n/2); we can ignore leaf nodes

			System.out.printf("Calling MaxH with %d as i\n", i);
			heapify(i); //call heapify helper function
		}
	}
	
	public void insert(T key) {	
		
		//TODO do we need to increment if get_size is automatic with ArrayList.size?
	   // Heap_size(A)++ //adding a new value to the heap
	   // A[get_size()] = infinity //make space at end of array for new value
		heap_size++;
		heap.add(null);
		
		heapIncreaseKey(get_size(), key); //start at the last index, i=Heap_size(A)
	}
	
	public void remove(int index) {
		//TODO remove - no notes on this method?? 
		heap.remove(index);
		heap_size--;
		build_heap();	
	}
	
	public ArrayList<T> sort(){
		//TODO sort
	    int n = get_size();
	    T first;
	    
	    for (int i = n; i >= 2; i--) {
	    	
	    	// swap values at index 1 and i
	    	first = heap.get(1);
	    	heap.set(1, heap.get(i));
	    	heap.set(i, first);
	        
	    	n--; //consider your heap to be one smaller

	        heapify(1); //restore max heap property
	    }	
		return heap;
	}
	
	 /**Accessors*/
	
	public T get_max() {
		
		//root heap[1]
		return heap.get(1);
	}
	
	public int get_parent(int i) {
		
		// A[i] = A[floor(i/2)]
		return (int) i / 2;
	}
	
	public int get_left(int i) {
		
		//A[2i]
		return (int) i * 2;
	}
	
	public int get_right(int i) {
		
		//A[2i + 1]
		return (int) (i * 2) + 1;
	}
	

	public int get_size() {
		
		//return heap.size() - 1;
		return heap_size;
	}
	
	public T get_element(int i) {
		
		return heap.get(i);
	}

	
	 /**Additional Operations*/
	
	@Override
	public String toString() {
		//TODO toString - how do we want to convert to string?
		return "PriorityQueue [sort()=" + sort() + ", get_max()=" + get_max() + ", get_size()=" + get_size() + "]";
	}
	
	
	public void displayArray() {
		//TODO how do we want to display Orders in PriorityQueue displayArray?
		for (int i=1; i <= get_size(); i++) {
			//System.out.println(i + ": " + heap.get(i).getOrderDate() + " + " + heap.get(i).getShipmentType());
			System.out.println(i + ": " + heap.get(i));
		}
	}

	
	
	
}


