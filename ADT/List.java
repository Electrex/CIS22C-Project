package ADT;
/**
 * Defines a generic doubly-linked list class
 * @author Mia Skinner
 * @author Albert Liu
 * CIS 22C, Lab 2
 */

import java.util.NoSuchElementException;

public class List<T extends Comparable<T>> {
	
    private class Node {
        private T data;
        private Node prev;
        private Node next;
        
        public Node(T data) {
            this.data = data;
            this.prev = null;
            this.next = null;
        }
    }
    
    private int length;
    private Node first;
    private Node last;
    private Node iterator;
    
    /****CONSTRUCTOR****/
    
    /**
     * Instantiates a new List with default values
     * @postcondition length = 0, first = null, last = null
     */
    public List() {
    	this.length = 0;
    	this.first = null;
    	this.last = null;
    	this.iterator = null;
 
    }
    
    /**
     * Instantiates a new List by copying another List 
     * @param original List to make a copy of
     * @postcondition a new List object, which is an identical but separate copy of the original List
     */
    public List(List<T> original) {
    	if (original.length == 0) {
    		length = 0;
    		first = null;
    		last = null;
    		iterator = null;
    	}
    	else {
    		Node temp = original.first;
    		while (temp != null) {
    			addLast(temp.data); //inserts into this
    			temp = temp.next;
    		}
    		iterator = null;
    	}
 
    }
    
    /****ACCESSORS****/
    
    /**
     * Returns the value stored in the first node
     * @precondition length > 0, first != null
     * @return the value stored at node first
     * @throws NoSuchElementException when precondition is violated
     */
    public T getFirst() throws NoSuchElementException{
    	
    	if (first == null || length == 0) {
    		throw new NoSuchElementException("getFirst(): List is Empty. No data to access!");
    	}
        return first.data;
    }
    
    /**
     * Returns the value stored in the last node
     * @precondition length > 0, last != null
     * @return the value stored in the node last
     * @throws NoSuchElementException when precondition is violated
     */
    public T getLast() throws NoSuchElementException{
    	
    	if (last == null || length == 0) {
    		throw new NoSuchElementException("getLast(): List is Empty. No data to access!");
    	}
        return last.data;
    }
    
    /**
     * Returns the current length of the list
     * @return the length of the list from 0 to n
     */
    public int getLength() {
        return this.length;
    }
    
    /**
     * Returns whether the list is currently empty
     * @return whether the list is empty
     */
    public boolean isEmpty() {
       return length == 0;
    }
    
    /**
     * Returns the value stored in the location the iterator is pointing
     * @precondition iterator is not null
     * @return the value stored at iterator.data
     * @throws NullPointerException when precondition is violated
     */
    public T getIterator() throws NullPointerException{
    	
    	if (iterator == null) {
    		throw new NullPointerException("getIterator(): iterator is not pointing to any element!");
    	}
        return iterator.data;
    }
    
    /**
     * Determines whether a List is sorted
     * by calling the recursive helper method
     * isSorted
     * Note: A List that is empty can be
     * considered to be (trivially) sorted
     * @return whether this List is sorted
     */
    public boolean isSorted() {
   
    	return isSorted(last);
    }
    
    /**
     * Recursively determines whether 
     * a List is sorted in ascending order
     * @return whether this List is sorted
     */
    private boolean isSorted(Node n) {
        if (n == first)
        	return true;
        else if (n.data.compareTo(n.prev.data) < 0)
        	return false;
        else {
        	return isSorted(n.prev);
        }
    }
    
    /**
     * Returns the index of the iterator
     * from 1 to n. Note that there is 
     * no index 0.
     * @precondition iterator != null
     * @return the index of the iterator
     * @throws NullPointerException when
     * the precondition is violated
     */
    public int getIndex() throws NullPointerException{
        if (iterator == null)
        	throw new NullPointerException("getIndex(): Can not get index. Iterator is null!");
    	Node temp = first;
    	for (int i = 1; i <= length; i++) {
        	if (temp == iterator)
        		return i;
        	temp = temp.next;
        }
    	return -1;
    }
    
    /******
     * Returns whether the iterator is offend
     * @return true if iterator is after the end of the list, false otherwise
     */
    public boolean offEnd()
    {
    	return (iterator==null); 
    }
    
    /******
     * Determines whether two Lists have the same data
     * in the same order
     * @param L the List to compare to this List
     * @return whether the two Lists are equal
     */
    @Override public boolean equals(Object obj)
    {
    	if (obj == this)
    		return true; 
    	else if (!(obj instanceof List))
    		return false; 
    	else
    	{
    		List L = (List) obj;
    		if (this.length != L.length)
    			return false;
    		else
    		{
    			Node temp1 = this.first;
    			Node temp2 = L.first;
    			while (temp1 != null)
    			{
    				if (temp1.data != temp2.data)
    					return false; 
    				temp1 = temp1.next;
    				temp2 = temp2.next; 
    			}
    			return true; 
    		}    		
    	}
    }
    
    /**
     * Searches the List for the specified
     * value using the iterative linear
     * search algorithm
     * @param value the value to search for
     * @return the location of value in the
     * List or -1 to indicate not found
     * Note that if the List is empty we will
     * consider the element to be not found
     * @postcondition: position of the iterator remains
     * unchanged!
     */
    public int linearSearch(T value) {
    	Node temp = first; 
    	for (int i = 1; i <= length; i ++)
    	{
    		if (value == temp.data)
    			return i;
    		temp = temp.next;
    	}
    	
        return -1;
    }
    
    /**
     * Returns the index from 1 to length
     * where value is located in the List
     * by calling the private helper method
     * binarySearch
     * @param value the value to search for
     * @return the index where value is 
     * stored from 1 to length, or -1 to
     * indicate not found
     * @precondition isSorted()
     * @postcondition the position of the
     * iterator must remain unchanged! 
     * @throws IllegalStateException when the
     * precondition is violated.
     */
    public int binarySearch(T value) throws IllegalStateException {

    	return binarySearch(1, length, value);
    }
    
    /**
     * Searches for the specified value in
     * the List by implementing the recursive
     * binarySearch algorithm
     * @param low the lowest bounds of the search
     * @param high the highest bounds of the search
     * @param value the value to search for
     * @return the index at which value is located
     * or -1 to indicate not found
     * @postcondition the location of the iterator
     * must remain unchanged
     */
    private int binarySearch(int low, int high, T value) {
        
    	
		if (high < low) {
			return -1; //not found
		}
		
		Node mid = first; 
		
		// int midindex = (low + high)/ 2; //midpoint formula
	    int midindex = low + (high - low) / 2; //midpoint formula

		
    	for (int i = 1; i < midindex; i ++)
    	{
    		mid = mid.next; 
    	}
		
		if (value == mid.data) {
			return midindex;
		} 
		else if(value.compareTo(mid.data) > 0) 
		{
	       return binarySearch(midindex + 1, high, value);
		} 
		else { 
	       return binarySearch(low, midindex - 1, value);
		}
	}
    
    /**Manipulation Procedures*/

    /**
         * Points the iterator at first
         * and then iteratively advances 
         * it to the specified index
         * @param index the index where
         * the iterator should be placed
         * @precondition 1 <= index <= length
         * @throws IndexOutOfBoundsException
         * when precondition is violated
         */
        public void moveToIndex(int index) throws IndexOutOfBoundsException{
        	if (index < 1 || index > length)
        		throw new IndexOutOfBoundsException("moveToIndex(): Invalid index. Index is out of bounds. ");
        	
        	pointIterator();
        	for (int i = 1; i < index; i ++)
        	{
        		advanceIterator();
        	}
        }
    
    
    /****MUTATORS****/
    
    /**
     * Creates a new first element
     * @param the data to insert at the 
     * front of the list
     * @postcondition length > 0, last & first != null, first.data = add.data
     */
    public void addFirst(T data) {
        
    	Node add = new Node(data);
    	if (first == null) {
    		first = last = add;
    	}
    	else {
    		add.next = first;
    		first.prev = add;
    		first = add;
    	}
    	length++;
    }
    
    /**
     * Creates a new last element
     * @param data the data to insert at the 
     * end of the list
     * @postcondition length > 0, last & first != null, last.data = add.data
     */
    public void addLast(T data) {
        
    	Node add = new Node(data);
    	if (last == null)
    		first = last = add; 
    	else {
    		last.next = add;
    		add.prev = last;
    		last = add;
    	}
    	length++; 
    }
    
    /**
    * removes the element at the front of the list
    * @precondition length != 0
    * @postcondition length = length - 1, first = first.next
    * @throws NoSuchElementException when precondition is violated
    */
    public void removeFirst() throws NoSuchElementException{
    	if (length == 0)
    		throw new NoSuchElementException("removeFirst(): Cannot remove from an empty List!");
    	else if (length == 1)
    		iterator = first = last = null; 
    	else {
    		if (iterator == first)
    			iterator = null;
    		first = first.next;
    		first.prev = null;
    	}

    	length --; 
    }
    
    /**
     * removes the element at the end of the list
     * @precondition length != 0
     * @postcondition length--, last = previous element
     * @throws NoSuchElementException when precondition is violated
     */
    public void removeLast() throws NoSuchElementException{
        if (length == 0)
        	throw new NoSuchElementException("removeLast(): Cannot remove from an empty List!");
        else if (length == 1)
        	iterator = first = last = null;
        else
        {
        	if (iterator == last)
        		iterator = null; 
        	last = last.prev; 
        	last.next = null;
        }
        
        length--; 
    }
    
    
    /**
     * moves the iterator to the start of the list
     * @postcondition iterator == first;
     */
    public void pointIterator(){

        	iterator = first;
    }
    
    
    /**
     * removes the element currently pointed to by the iterator
     * @precondition length != 0, iterator not null
     * @postcondition iterator is null, length--
     * @throws NullPointerException when precondition is violated
     */
    public void removeIterator() throws NullPointerException{
    	if (length == 0) 
        	throw new NullPointerException("removeIterator(): Cannot remove from an empty List!");
    	else if (iterator == null) 
        	throw new NullPointerException("removeIterator(): Iterator is not pointing to any element!");
        else if (length == 1)
        	first = last = null;
        else if (iterator == first){
        	first = iterator.next;
        	first.prev = null;
        }
        else if (iterator == last){
        	last = iterator.prev;
        	last.next = null;
        }
        else {
        	iterator.prev.next = iterator.next;
        	iterator.next.prev = iterator.prev;

        }
        iterator = null;
        length--;
    }
    
    /**
     * adds element after the location currently pointed to by the iterator, if List is empty, creates first element and points iterator to first
     * @param the data to insert at after the location of the iterator
     * @precondition iterator != null
     * @postcondition length++
     * @throws NullPointerException when precondition is violated
     */
    public void addIterator(T data) throws NullPointerException{
    	//if (length == 0){
        //	this.addFirst(data);
        //	iterator = first;
        //}
    	if (iterator == null) 
        	throw new NullPointerException("addIterator(): Location not provided, iterator is null!");    	
        else if (iterator == last){
        	this.addLast(data);
        }
        else {
        	Node add = new Node(data);
        	
        	add.prev = iterator;
        	add.next = iterator.next;
        	iterator.next = add;
        	add.next.prev = add;
        	length++;
        }  
    }
     
    /**
     * moves the iterator up one node
     * @precondition iterator != null, iterator.next != null
     * @postcondition iterator == iterator.next
     * @throws NullPointerException when precondition is violated
     */
    public void advanceIterator() throws NullPointerException{
        if (iterator == null) 
        	throw new NullPointerException("advanceIterator(): Location cannot advance, iterator is null!"); 
        else if (iterator.next == null) 
        	iterator = null; 
        else
        	iterator = iterator.next;
 
    }
    
    /**
     * moves the iterator up one node
     * @precondition iterator != null, iterator.prev != null
     * @postcondition iterator == iterator.prev
     * @throws NullPointerException when precondition is violated
     */
    public void reverseIterator() throws NullPointerException{
        if (iterator == null) 
        	throw new NullPointerException("advanceIterator(): Location cannot advance, iterator is null!"); 
        else if (iterator.prev == null) 
        	iterator = null; 
        else
        	iterator = iterator.prev;
 
    }
    
    /****ADDITIONAL OPERATIONS****/
    
    /**
     * List with each value separated by a blank space
     * At the end of the List a new line
     * @return the List as a String for display
     */
    @Override public String toString() {
    	String result = "";
    	Node temp = first;
    	while(temp!= null)
    	{
    		// For the purposes of this Lab, we will simply have the data items displayed on a single line, each separated by a blank space.
    		result += temp.data + " ";
    		temp = temp.next; 
    	}
    	result += "\n";
       return result;
    }
    
    /**
     * prints the content of the linked list to the screen in the format #. <element> followed by a new line. 
     */
    public void printNumberedList()
    {
    	int counter = 1;
    	
    	Node temp = first;
    	while (counter <= length)
    	{
    		System.out.printf("%d. %s\n", counter, (String) temp.data); 
    		temp = temp.next; 
    		counter++;
    	}
    }
    
    /**
     * Prints a linked list to the console
     * in reverse by calling the private 
     * recursive helper method printReverse
     */
    public void printReverse() {
    	printReverse(last);
    }
    
    /**
     * Prints a linked list to the console
     * recursively (no loop)
     * in reverse order from last to first
     * Each element separated by a space
     * Should print a new line after all
     * elements have been displayed
     */    
    private void printReverse(Node n) {
        if (n.prev == null)
    	{
        	System.out.print(first.data);
        	System.out.println();
        }
        else
        {
        	System.out.print(n.data + " "); 
        	printReverse(n.prev);
        }
    } 

}

