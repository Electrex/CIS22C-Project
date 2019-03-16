/**
 * Defines the a doubly-linked list class
 * @author Eugene Kim
 * CIS 22C, Group Project
 */

import java.util.NoSuchElementException;

public class List<T extends Comparable<T>> {
    private class Node {
        private T data;
        private Node next;
        private Node prev;

        public Node(T data) {
            this.data = data;
            this.next = null;
            this.prev = null;
        }
    }

    private int length;
    private Node first;
    private Node last;
    private Node iterator;

    /****CONSTRUCTOR****/

    /**
     * Instantiates a new List with default values
     * @postcondition Creates a new list with null values and length = 0
     */
    public List() {
        length = 0;
        first = null;
        last = null;
        iterator = null;

    }

    /**
     * Instantiates a new List by copying another List
     * @param original the List to make a copy of
     * @postcondition a new List object, which is an identical
     * but separate copy of the List original
     */
    public List(List<T> original) {
        if(original.length == 0)
        {
            this.first = this.last = this.iterator = null;
            this.length = 0;
        }
        else
        {
            Node temp = original.first;

            while(temp != null)
            {
                addLast(temp.data);
                temp = temp.next;
            }

            this.iterator = null;
        }

    }

    /****ACCESSORS****/

    /**
     * Returns the value stored in the first node
     * @precondition List is not empty
     * @return the type T value stored at node first
     * @throws NoSuchElementException when precondition is violated
     */
    public T getFirst() throws NoSuchElementException{

        if(length == 0)
        {
            throw new NoSuchElementException("getFirst: List is Empty. No data to access!");
        }

        return first.data;

    }

    /**
     * Returns the value stored in the last node
     * @precondition List is not empty
     * @return the type T value stored in the node last
     * @throws NoSuchElementException when precondition is violated
     */
    public T getLast() throws NoSuchElementException{

        if(length == 0)
        {
            throw new NoSuchElementException("getLast: List is Empty. No data to access!");
        }

        return last.data;
    }

    /**
     * Returns the current length of the list
     * @return the length of the list from 0 to n
     */
    public int getLength() {
        return length;
    }

    /**
     * Returns whether the list is currently empty
     * @return whether the list is empty
     */
    public boolean isEmpty() {
        return length == 0;
    }

    /**
     * returns the element currently pointed at by the iterator
     * @precondition iterator is not off end/null
     * @return the type T value of the node pointed at by the iterator
     * @throws NullPointerException when precondition is violated
     */
    public T getIterator() throws NullPointerException {
        if(offEnd())
        {
            throw new NullPointerException("getIterator(): Cannot return data from an iterator that is off end!");
        }
        return iterator.data;
    }

    /**
     * returns whether the iterator is off the end of the list, i.e. is NULL
     * @return whether the iterator is null
     */
    public boolean offEnd() {
        return iterator == null;
    }

    /**
     * Determines whether two Lists have the same data
     * in the same order
     * @param o the object to compare to this List
     * @return whether the two Lists are equal
     */
    @SuppressWarnings("unchecked")
    @Override
    public boolean equals(Object o) {
        if(o == this)
        {
            return true;
        }
        else if(!(o instanceof List))
        {
            return false;
        }
        else
        {
            List<T> L = (List<T>) o;

            if(this.length != L.length)
            {
                return false;
            }
            else
            {
                Node temp1 = this.first;
                Node temp2 = L.first;

                while(temp1 != null)
                {
                    if(!(temp1.data.equals(temp2.data))) {
                        return false;
                    }
                    temp1 = temp1.next;
                    temp2 = temp2.next;
                }

                return true;

            }
        }
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
        if(length == 0)
        {
            return true;
        }
        
        Node temp = first;
        
        return isSorted(temp);
    }

    /**
     * Recursively determines whether 
     * a List is sorted in ascending order
     * @return whether this List is sorted
     */
    private boolean isSorted(Node n) { 
        if(n == last) // base case of when comparing last node
        {
            return true;
        }
        else if(n.data.compareTo(n.next.data) > 0) // If next value is not bigger, immediately get out of recursive
        {
            return false;
        }
        else
        {
            return (isSorted(n.next));
        }
    }
    
    /**
     * Returns the index of the iterator
     * from 1 to n. Note that there is 
     * no index 0.
     * @precondition Iterator is not null / !offEnd()
     * @return the index of the iterator
     * @throws NullPointerException when
     * the precondition is violated
     */
    public int getIndex() throws NullPointerException{
        if(iterator == null)
        {
            throw new NullPointerException("getIndex(): Iterator is off end! Cannot return index.");
        }
        else
        {
            Node temp = first;
            int index = 1;
            
            for(int i = 1; i <= length; i++)
            {
                if(iterator == temp)
                {
                    index = i;
                }
                
                temp = temp.next;
            }
            
            return index;
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
        
        for(int i = 1; i <= length; i++)
        {
            if(temp.data.compareTo(value) == 0)
            {
                return i;
            }
            
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
        if(!isSorted())
        {
            throw new IllegalStateException("binarySearch(): List is not sorted. Cannot perform binary search!");
        }
        else
        {
            return binarySearch(1, length, value);
        }
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
        
        if(high < low)
        {
            return -1;
        }
        
        int mid = (low + high) / 2;
        
        Node temp = first;
        
        for(int i = 1; i < mid; i++)
        {
            temp = temp.next;
        }
       
        if(temp.data.compareTo(value) == 0)
        {
            return mid;
        }
        else if(value.compareTo(temp.data) < 0  )
        {
            return binarySearch(low, mid - 1, value);
        }
        else
        {
            return binarySearch(mid + 1, high, value);
        }
    }

    /****MUTATORS****/

    /**
     * Creates a new first element
     * @param data the data to insert at the 
     * front of the list
     * @postcondition Creates a new node containing the data specified in the parameter at the front of the list
     */
    public void addFirst(T data) {
        Node N = new Node(data);

        if(first == null)
        {
            first = last = N;
        }
        else
        {
            N.next = first;
            first.prev = N;
            first = N;
        }

        length++;
    }

    /**
     * Creates a new last element
     * @param data the data to insert at the 
     * end of the list
     * @postcondition Creates a node containing the value specified in the parameter at the end of the list
     */
    public void addLast(T data) {
        Node N = new Node(data);

        if(last == null)
        {
            last = first = N;
        }
        else
        {
            last.next = N;
            N.prev = last;
            last = N;
        }

        length++;
    }

    /**
     * removes the element at the front of the list
     * @precondition list is not empty
     * @postcondition removes first node of the list
     * @throws NoSuchElementException when precondition is violated
     */
    public void removeFirst() throws NoSuchElementException{
        if (isEmpty())
        {
            throw new NoSuchElementException("removeFirst(): Cannot remove from an empty List!");
        }
        else if (length == 1)
        {
            first = last = null;
        }
        else
        {
            first = first.next;
            first.prev = null;
        }

        length--;
    }

    /**
     * removes the element at the end of the list
     * @precondition list is not empty
     * @postcondition removes the last node of the list
     * @throws NoSuchElementException when precondition is violated
     */
    public void removeLast() throws NoSuchElementException{
        if(isEmpty())
        {
            throw new NoSuchElementException("removeLast(): Cannot remove from an empty List!");
        }
        else if (length == 1) {
            last = first = null;
        } else {
            last = last.prev;
            last.next = null;
        }
        length--;
    }

    /**
     * moves the iterator to the start of the list
     */
    public void pointIterator() {
        iterator = first;
    }

    /**
     * removes the element currently pointed to by the iterator. 
     * @precondition iterator is not null
     * @postcondition Iterator then points to NULL.
     * @throws NullPointerException when precondition is violated
     */
    public void removeIterator() throws NullPointerException{
        if(offEnd())
        {
            throw new NullPointerException("removeIterator(): Cannot remove on an iterator that is null!");
        } 
        else if(iterator == first)
        {
            removeFirst();
        }
        else if(iterator == last)
        {
            removeLast();
        }
        else
        {
            iterator.prev.next = iterator.next;
            iterator.next.prev = iterator.prev;
            length--;
        }

        iterator = null;
    }

    /**
     * inserts an element after the node currently pointed to by the iterator
     * @precondition iterator is not null
     * @postcondition Adds a new node containing the element specified in the parameter after the node pointed to by the iterator
     * @throws NullPointerException when precondition is violated
     */
    public void addIterator(T data) throws NullPointerException{
        if(offEnd())
        {
            throw new NullPointerException("addIterator(): Cannot insert on an iterator that is off end!");
        } 
        else if(iterator == last)
        {
            addLast(data);
        }
        else 
        { 
            Node N = new Node(data);
            N.next = iterator.next;
            iterator.next = N;
            N.next.prev = N;
            N.prev = iterator;
            length++;
        }
    }

    /**
     * moves the iterator up by one node
     * @precondition iterator is not null
     * @throws NullPointerException when precondition is violated
     */
    public void advanceIterator() throws NullPointerException{
        if(offEnd())
        {
            throw new NullPointerException("advanceIterator(): Cannot advance an iterator that is off end!");
        } 
        iterator = iterator.next;
    }

    /**
     * moves the iterator down by one node
     * @precondition iterator is not null
     * @throws NullPointerException when precondition is violated
     */
    public void reverseIterator() throws NullPointerException{
        if(offEnd())
        {
            throw new NullPointerException("reverseIterator(): Cannot reverse an iterator that is off end!");
        } 
        iterator = iterator.prev;
    }
    
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
        if(index < 1 || index > length)
        {
            throw new IndexOutOfBoundsException("moveToIndex(): Index is out of bounds! Cannot move to index.");
        }
        else
        {
            pointIterator();
            
            for(int i = 1; i < index; i++)
            {
                advanceIterator();
            }
        }
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
        while(temp != null) {          
            result+=temp.data + " ";
            temp = temp.next;
        }
        result+="\n";
        return result;
    }

    /**
     * prints the contents of the linked list to the screen in the format #. <element> followed by a newline
     */
    public void printNumberedList() {
        Node temp = first;
        for(int i = 1; i <= length; i++)
        {
            //Convert i to a string

            String number = Integer.toString(i);

            System.out.println(number + ". " + temp.data);
            temp = temp.next;
        }
    }   

    /**
     * Prints a linked list to the console
     * in reverse by calling the private 
     * recursive helper method printReverse
     */
    public void printReverse() {
        Node temp = first;
        printReverse(temp);
        System.out.println();
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
        if(n == null)
        {
            return;
        }
        
        printReverse(n.next);
        
        System.out.print(n.data + " ");
    }     
}

