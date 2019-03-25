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
	 * @precondition Given a valid max heap (except for a single node), Precondition: left(i) and right(i) are both valid max heaps
	 * @postcondition valid max heap
	 * @param i, the invalid index
	 */
	private void heapify(int length, int i){
		
		int index_of_max = i;
		int left = get_left(i); //get the index of the left child of A[i] and store as l
		int right = get_right(i); //get the index of the right child of A[i] and store r

	
		//Check if l is off the end of the array (heap) AND compare A[i] to its left child
		if (left <= length && heap.get(left).compareTo(heap.get(i)) > 0) {
	
		    index_of_max = left; //update index_of_max if left is bigger
		}
	
		//Check if r is off the end of the array (heap) AND compare A[r] to current max value
	
		if (right <= length && heap.get(right).compareTo(heap.get(index_of_max)) > 0) {
	
			index_of_max = right; //update index_of_max if right is bigger
		}
	
		if (i != index_of_max) {//if A[i] was not bigger than its two children
			T oldMax = heap.get(index_of_max);
			T newMax = heap.get(i);
		    heap.set(index_of_max, newMax); //swap, so now A[i] stored at A[index_of_max]
		    heap.set(i, oldMax);
	
		    heapify(length, index_of_max); //recursively move through tree until restore heap property
		}
	}
	
	/**
	 * Helper algorithm of heap insert
	 * Compares the new key to its parent and swaps if necessary, and keeps swapping until the heap property is restored (bubbles up)
	 * @postcondition heap property restored for i and i's children
	 * @param i, index
	 * @param key, new value
	 */
	private void heapIncreaseKey(int i, T key) {
	    if(heap.get(i) == null || key.compareTo(heap.get(i)) > 0) {

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
	
    /**
     * Default constructor for PriorityQueue (Heap)
     * sets heap_size to 0, initializes heap's arrayList, and fills index 0 with null
     */
	public PriorityQueue(){
		heap_size = 0;
		heap = new ArrayList<T>();
		heap.add(null); // fill in placeholder for index zero
	}

	/*******
	 * Copy constructor
	 * @param copy
	 */
	public PriorityQueue(PriorityQueue<T> copy) 
	{
        if(copy.heap_size == 0)
        {
    		heap_size = 0;
    		heap = new ArrayList<T>();
    		heap.add(null);    		
        }
        else
        	for (int idx = 0; idx < copy.heap_size; idx ++)
        		this.heap.set(idx, copy.get_element(idx));
	}
	
	
	
	/**Mutators*/
	
	/**
	 * Takes an unordered array (heap instance var), and turns it into a valid Heap
	 * Starts in the middle of the array and swaps nodes where parents are smaller than children (bubbles values down)
	 * until values have reached their correct location (uses heapify helper algorithm to do the bubbling)
	 * runtime of O(n)
	 * @postcondition a valid Heap
	 */
	public void build_heap() {

		int n = get_size();

		for (int i = get_size()/2; i >= 1; i--) {//start at floor(n/2); we can ignore leaf nodes

			heapify(n, i); //call heapify helper function
		}
	}
	
	/**
	 * Inserts the value at the bottom of the heap, as far left as possible (at the very end of the array)
	 * Calls heapIncreaseKey helper
	 * @postcondition a valid heap, heap_size++
	 * @param key
	 */
	public void insert(T key) {	

		heap_size++; //adding a new value to the heap
		heap.add(null); //make space at end of array for new value
		
		heapIncreaseKey(get_size(), key); //start at the last index, i=Heap_size(A)
	}
	
	/**
	 * Removes value at index location in heap and restores heap property
	 * @precondition a non-empty heap (heap_size >= 1)
	 * @postcondition a valid heap, heap_size--
	 * @throws IndexOutOfBoundsException
	 * @param a valid heap index
	 */
	public void remove(int index) throws IndexOutOfBoundsException {

		if (index <= 0 || index > get_size()) 
			throw new IndexOutOfBoundsException("remove(): Invalid index. No element found!");
		
		heap.remove(index);
		heap_size--;
		build_heap();	
		
	}
	
	/**
	 * Returns a sorted ArrayList. Exchanges the right most bottom key with root (swap),
	 * then rebuilds the heap (heapify) and repeats
	 * @precondition a valid heap
	 * @postcondition restores heap to a valid heap
	 * @return sorted ArrayList of values to display lowest to highest priority
	 */
	public ArrayList<T> sort(){
		
		int n = get_size();
	    T first;
	    
	    for (int i = n; i >= 2; i--) {
	    	
	    	// swap values at index 1 and i
	    	first = heap.get(1);
	    	heap.set(1, heap.get(i));
	    	heap.set(i, first);
	        
	    	n--; //consider your heap to be one smaller

	        heapify(n, 1); //restore max heap property
	    }	
	    
	    ArrayList<T> sortedHeap = new ArrayList<T>(heap);
	    build_heap();
	    
		return sortedHeap;
	}
	
	 /**Accessors*/
	
	
	/**
	 * Returns the max element of the heap (root, index 1)
	 * @precondition a valid heap
	 * @return returns max element (root)
	 */
	public T get_max() {
		
		//root heap[1]
		return heap.get(1);
	}
	
	/**
	 * Returns the parent of the element at i
	 * @precondition a valid heap, a valid index i 
	 * @param index i
	 * @throws IndexOutOfBoundsException
	 * @return parent of i
	 */
	public int get_parent(int i) throws IndexOutOfBoundsException {
		if (i <= 0 || i > get_size())
			throw new IndexOutOfBoundsException("get_parent(): Invalid Index! Cannot get parent of i.");
		
		// A[i] = A[floor(i/2)]
		return (int) i / 2;
	}
	
	/**
	 * Returns the left child of the element at i
	 * @precondition a valid heap, a valid index i
	 * @param i
	 * @throws IndexOutOfBoundsException
	 * @return left child
	 */
	public int get_left(int i) throws IndexOutOfBoundsException {
		
		if (i <=0 || i > get_size())
			throw new IndexOutOfBoundsException("get_left(): Invalid Index! Cannot get left child of i.");
		//A[2i]
		return (int) i * 2;
	}
	
	/**
	 * Returns the right child of the element at i
	 * @precondition a valid heap, a valid index i
	 * @param index i
	 * @throws IndexOutOfBoundsException
	 * @return right child
	 */
	public int get_right(int i) throws IndexOutOfBoundsException {
		
		if (i <=0 || i > get_size())
			throw new IndexOutOfBoundsException("get_right(): Invalid Index! Cannot get right child of i.");
		//A[2i + 1]
		return (int) (i * 2) + 1;
	}
	
	/**
	 * Returns the size of the heap
	 * @return heap_size
	 */
	public int get_size() {
		
		//return heap.size() - 1;
		return heap_size;
	}
	
	/**
	 * Returns element at index i in heap
	 * @precondition a valid index i
	 * @param i
	 * @throws IndexOutOfBoundsException
	 * @return element at i
	 */
	public T get_element(int i) throws IndexOutOfBoundsException {
		if (i <= 0 || i > get_size())  
			throw new IndexOutOfBoundsException("get_element(): Invalid Index! Not a valid heap index, cannot get element.");
		
		return heap.get(i);
	}

	
	 /**Additional Operations*/
	
	/**
	 * Returns PriorityQueue info as a String
	 * @return String of heap info
	 */
	@Override
	public String toString() {
		return "PriorityQueue: \nheap = " + heap + "\nsort() = " + sort() +"\nget_max() = " + get_max() + "\nget_size() = " + get_size();
	}
	
	/**
	 * Prints the heap's priority queue to screen (high to low - reverse of sort())
	 */
	public void displayArray() {
		
		ArrayList<T> sorted = sort();
		
		int priority_num = 1;
		for (int i=get_size(); i >= 1; i--) {
			System.out.println(priority_num++ + ": " + sorted.get(i));
		}
	}

	
	
	
}


