package ADT;
/**
 * Hash.java
 * @author Eugene Kim
 * CIS 22C, Lab 7
 */
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.NoSuchElementException;

public class Hash<T extends Comparable<T>> {

    private int numElements;
    private ArrayList<List<T>> Table;

    /**
     * Constructor for the Hash.java
     * class. Initializes the Table to
     * be sized according to value passed
     * in as a parameter
     * Inserts size empty Lists into the
     * table. Sets numElements to 0
     * @param size the table size
     */
    public Hash(int size) {
        numElements = 0;
        Table = new ArrayList<List<T>>(size);

        for(int i = 0; i < size; i++)
        {
            List<T> temp = new List<T>();
            Table.add(i, temp);
        }
    }

    /**Accessors*/

    /**
     * Returns the hash value in the Table
     * for a given key by taking modulus
     * of the hashCode value for that key
     * and the size of the table
     * @param t the key
     * @return the index in the Table
     */
    private int hash(T t) {
        int code = t.hashCode();
        return code % Table.size();
    }

    /**
     * Counts the number of keys at this index
     * @param index the index in the Table
     * @precondition 0 <= index < Table.length
     * @return the count of keys at this index
     * @throws IndexOutOfBoundsException
     */
    public int countBucket(int index) throws IndexOutOfBoundsException{
        if(index < 0 || index >= Table.size())
        {
            throw new IndexOutOfBoundsException("countBucket(): Index is out of bounds! Cannot count.");
        }
        else
        {
            return Table.get(index).getLength();
        }
    }

    /**
     * Returns total number of keys in the Table
     * @return total number of keys
     */
    public int getNumElements() {
        return numElements;
    }

    /**
     * Searches for a specified key in the Table
     * @param t the key to search for
     * @return the index in the Table (0 to Table.length - 1)
     * or -1 if t is not in the Table
     */
    public int search(T t) {
        int bucket = hash(t);
        
        if(Table.get(bucket).linearSearch(t) != -1) 
        {
            return bucket;
        }
        
        return -1;
    }
    
    /**
     * Searches for a specified key in the Table
     * @precondition Key t must exist in the Table
     * @param t the key to search for
     * @throws NoSuchElementException when precondition is violated
     * @return T the key that was found in the table
     * 
     */
    public T searchKey(T t) throws NoSuchElementException {
        int bucket = hash(t);
        
        int index = Table.get(bucket).linearSearch(t);
        
        if(index == -1)
        {
            throw new NoSuchElementException("searchKey(): Object does not exist! Cannot return a value.");
        }
        else
        {
            Table.get(bucket).pointIterator();
            
            for(int i = 0; i < Table.get(bucket).getLength(); i++)
            {
                if(Table.get(bucket).getIterator().compareTo(t) == 0)
                {
                    return Table.get(bucket).getIterator();
                }
                else
                {
                    Table.get(bucket).advanceIterator();
                }
            }
            
            return null;
        }
    }


    /**Manipulation Procedures*/

    /**
     * Inserts a new key in the Table
     * calls the hash method to determine placement
     * @param t the key to insert
     */
    public void insert(T t) { 
        int bucket = hash(t);
        Table.get(bucket).addLast(t);
        numElements++;
    } 


    /**
     * removes the key t from the Table
     * calls the hash method on the key to
     * determine correct placement
     * has no effect if t is not in
     * the Table
     * @param t the key to remove
     */
    public void remove(T t) {
        if(search(t) != -1)
        {
            
            int bucket = hash(t);
            
            Table.get(bucket).pointIterator();
            
            for(int i = 0; i < Table.get(bucket).getLength(); i++)
            {
                if(Table.get(bucket).getIterator().equals(t))
                {
                    Table.get(bucket).removeIterator();
                }
                else
                {
                    Table.get(bucket).advanceIterator();
                }
            }
            
            numElements--;
        }
    }

    /**Additional Methods*/

    /**
     * Prints all the keys at a specified
     * bucket in the Table. Each key displayed
     * on its own line, with a blank line 
     * separating each key
     * Above the keys, prints the message
     * "Printing bucket #<bucket>:"
     * Note that there is no <> in the output
     * @param bucket the index in the Table
     */
    public void printBucket(int bucket) {
        System.out.println("Printing bucket #" + bucket);
        System.out.println();

        Table.get(bucket).pointIterator();

        for(int i = 0; i < Table.get(bucket).getLength(); i++)
        {
            System.out.println(Table.get(bucket).getIterator());
            System.out.println();
            Table.get(bucket).advanceIterator();
        }
        
        System.out.println();
    }

    /**
     * Prints the first key at each bucket
     * along with a count of the total keys
     * with the message "+ <count> -1 more 
     * at this bucket." Each bucket separated
     * with to blank lines. When the bucket is 
     * empty, prints the message "This bucket
     * is empty." followed by two blank lines
     */
    public void printTable() {
        for(int i = 0; i < Table.size(); i++)
        {
            if(Table.get(i).getLength() == 0)
            {
                System.out.println("Bucket: " + i);
                System.out.println("This bucket is empty.\n");
            }
            else
            {
                System.out.println("Bucket: " + i);
                System.out.println(Table.get(i).getFirst());
                System.out.println("+ " + (countBucket(i) - 1) + " more at this bucket");
                System.out.println();
            }
        }
    }
    
    public ArrayList<T> getObjects()
    {
        ArrayList<T> temp = new ArrayList<T>();
        
        for(int i = 0; i < Table.size(); i++)
        {
            for(int j = 0; j < Table.get(i).getLength(); j++)
            {
                Table.get(i).pointIterator();
                temp.add(Table.get(i).getIterator());
                Table.get(i).advanceIterator();
            }
        }
        
        return temp;
    }
    
    /**
     * @precondition File must exist
     * @param filename the name of the file to write the output to
     * @throws FileNotFoundException if precondition is violated
     * @postcondition Hash table is written in the output file
     */
    public void writeToFile(String filename) throws FileNotFoundException
    {
        PrintWriter outfile = new PrintWriter(filename);
        outfile.write(this.toString());
        outfile.close();
    }
    
    /**
     * Outputs the hash table for display by going through each lists in the index and displaying their toString information
     */
    @Override 
    public String toString()
    {
        String result = "";
        for(int i = 0; i < Table.size(); i++)
        {
            Table.get(i).pointIterator();
            
            for(int j = 0; j < Table.get(i).getLength(); j++)
            {
                result += (Table.get(i).getIterator());
                Table.get(i).advanceIterator();
            }
        }
        return result;
    }
}