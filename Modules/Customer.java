package Modules;

/**
 * Customer.java
 * @author Eugene Kim
 * CIS 22C
 */

 
import ADT.PriorityQueue;

public class Customer implements Comparable<Customer> {
    private String firstName;
    private String lastName;
    private String address;
    private String username;
    private String password;
    private PriorityQueue<Order> orders;
    
    /****CONSTRUCTOR****/

    /**
     * Instantiates a new Customer with default values
     * @postcondition Creates a new customer with default values
     */
    public Customer()
    {
        firstName = lastName = address = username = password = "";
        orders = new PriorityQueue<Order>();
    }
    
    /**
     * Instantiates a new Customer with first name and last name that is passed in
     * @postcondition Creates a new customer with first and last name 
     */
    public Customer(String fname, String lname)
    {
        firstName = fname;
        lastName = lname;
        address = username = password = "";
        
        orders = new PriorityQueue<Order>();
    }

    /**
     * Instantiates a new Customer with values that are passed in
     * @postcondition Creates a new customer with values that are passed in
     */
    public Customer(String fname, String lname, String add, String userN, String pass)
    {
        firstName = fname;
        lastName = lname;
        address= add;
        username = userN;
        password = pass;

        orders = new PriorityQueue<Order>();
    }
    
    /**
     * Accesses the full name of the customer
     * @return the customer's full name
     */
    public String getFullName()
    {
        return firstName + " " + lastName;
    }

    /**
     * Accesses the address of the customer
     * @return the customer's address
     */
    public String getAddress()
    {
        return address;
    }
  
    /**
     * Accesses the customer's first name
     * @return the customer's first name
     */
    public String getFirstName() 
    {
        return firstName;
    }
    
    /**
     * Accesses the last name of the customer
     * @return the customer's last name
     */
    public String getLastName() 
    {
        return lastName;
    }
    
    /**
     * Accesses the customer's user name
     * @return the customer's user name
     */
    public String getUsername()
    {
        return username;
    }
    
    /**
     * Accesses the customer's orders
     * @return the customer's orders
     */
    public PriorityQueue<Order> getOrders()
    {
        return orders;
    }
    
    /**
     * Verifies the password of the customer
     * @param password the password that is being checked 
     * @return whether if password matches with the customer
     */
    public boolean verifyPassword(String password)
    {
        if(this.password.equals(password))
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    
    
    /**
     * Sets the first name of the customer
     * @param first the first name that is being set
     */
    public void setFirstName(String first)
    {
        firstName = first;
    }
    
    /**
     * Sets the customer's last name
     * @param last the customer's last name
     */
    public void setLastName(String last)
    {
        lastName = last;
    }
    
    /**
     * Sets the customer's username with the value that is being passed
     * @param user name the username that is being set
     */
    public void setUsername(String username)
    {
        this.username = username;
    }
    
    /**
     * Sets the customer's address
     * @param address the address that is being set
     */
    public void setAddress(String address)
    {
        this.address = address;
    }

    /**
     * Inserts a order to the list of customer orders
     * @param order the order that is being inserted to the customer's list of orders
     */
    public void insertOrder(Order o)
    {
        orders.insert(o);
    }
    
    /**
     * Returns a consistent hash code for 
     * each Customer by summing the Unicode values
     * of each character in the key
     * Key = first name + last name
     * @return the hash code
     */
    @Override 
    public int hashCode()
    {
        String key = "";
        key = firstName + lastName;
        
        int sum = 0;
        
        for(int i = 0; i < key.length(); i++)
        {
            sum += (int) key.charAt(i);
        }
        
        return sum;
    }
    
    /**
     * Compares two Customer objects to determine ordering
     * Returns 0 if the two items are equal
     * Return -1 if this Customers's full name comes alphabetically
     * before the other Customer's full name.
     * Returns 1 if the other Customer's full name comes
     * alphabetically before this customer's full name
     * If the two customer's full names are the same, will
     * differentiate by username
     * @param the other Movie object to compare to this
     * @return 0 (same movie), -1 (this movie ordered first)
     * or 1 (the other movie ordered first) 
     */
    public int compareTo(Customer c)
    {
        if(c.equals(this))
        {
            return 0;
        }
        else if(!c.getFullName().equals(this.getFullName())) 
        {
            if(this.getFullName().compareTo(c.getFullName()) < 0)
            {
                return -1;
            }
            else
            {
                return 1;
            }
        }
        else
        {
            if(this.username.compareTo(c.username) < 0)
            {
                return -1;
            }
            else
            {
                return 1;
            }
        }
    }
    
    /**
     * Determines whether two Customer objects are 
     * equal by comparing user name and password
     * @param o the object to compare
     * @return whether the Customers are equal
     */
    @Override 
    public boolean equals(Object o)
    {
        if(o == this)
        {
            return true;
        }
        else if(!(o instanceof Customer))
        {
            return false;
        }
        else
        {
            Customer temp = (Customer) o;
            return (temp.username.equals(this.username) && this.password.equals(temp.password));
        }
    }
    
    /**
     * Formats the customer for display, using
     * the following format:
     * firstName, lastName, user name, password, address
     */
    @Override
    public String toString()
    {
        String result = firstName + "," + lastName + "," + username + "," + password + "," + address + "\n";
        return result;
    }
}
