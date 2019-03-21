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

    private PriorityQueue orders;
    
    public Customer()
    {
        firstName = lastName = address = username = password = "";

            
        orders = new PriorityQueue();
        
    }
    
    public Customer(String fname, String lname, String password)
    {
        firstName = fname;
        lastName = lname;
        this.password = password;
        address = username = "";
        
        orders = new PriorityQueue();
    }

    public Customer(String fname, String lname, String add, String userN, String pass)
    {
        firstName = fname;
        lastName = lname;
        address= add;
        username = userN;
        password = pass;

        orders = new PriorityQueue();
    }
    
    public String getFullName()
    {
        return firstName + " " + lastName;
    }
    
    public String getAddress()
    {
        return address;
    }
  
    public String getFirstName() 
    {
        return firstName;
    }
    
    public String getLastName() 
    {
        return lastName;
    }
    
    
    public String getUsername()
    {
        return username;
    }
    
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
    
    public void setFirstName(String first)
    {
        firstName = first;
    }
    
    public void setLastName(String last)
    {
        lastName = last;
    }
    
    public void setUsername(String username)
    {
        this.username = username;
    }
    
    public void setAddress(String address)
    {
        this.address = address;
    }

    public void insertOrder(Order o)
    {
        orders.insert(o);
    }
    
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
    
    @Override
    public String toString()
    {
        String result = "First Name: " + firstName
                + ", Last Name: " + lastName
                + ", Username: " + username
                + ", Password: " + password
                + ", Address: " + address 
                + "\n";
        return result;
    }
}
