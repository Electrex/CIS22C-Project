package Modules;

public class Employee implements Comparable<Employee>{
	
	private String fullname; 
	private String username, password; 
	
	public Employee()
	{
		fullname = ""; 
		username = ""; 
		password = ""; 
	}
	
	public Employee(String username, String password, String fullname)
	{
		this.fullname = fullname;
		this.username = username; 
		this.password = password; 
	}
	
	public Employee(String username, String password)
	{
		this.fullname = "";
		this.username = username; 
		this.password = password; 
	}

	public String getUsername() {
		return username;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String newfullname) {
		this.fullname = newfullname;
	}

	public void setUsername(String newusername) {
		this.username = newusername;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String newpassword) {
		this.password = newpassword;
	}

    /**
     * Formats the movie for display, using
     * the following format:
     * Title: <title>
     * Director: <director>
     * Year: <year>
     * Gross in Millions: $<grossMillions>
     * Note that there should be no <>
     * in the resulting String
     */
    @Override public String toString() {
        String result = username + "," + password + "," + fullname + "\n";
        return result;
    }
    
    /**
     * Determines whether two Employee objects are 
     * equal by comparing username and password
     * @param the second employee object 
     * @return whether the employees are equal
     */
    @Override public boolean equals(Object o) {
    	if (o == this)
    		return true; 
    	else if (!(o instanceof Employee))
    		return false; 
    	else
    	{
    		Employee M = (Employee) o;
    		if (this.compareTo(M) == 0)
    			return true;
    	}
        return false;
    }
    
    /**
     * Compares two Employee objects to determine ordering
     * Returns 0 if the two items are equal
     * Return -1 if this Employee's username + password comes alphabetically
     * before the other Employee's username + password.
     * Returns 1 if the other Employee's username + password comes
     * alphabetically before this Employee's username + password
     * @param the other Employee object to compare to this
     * @return 0 (same Employee), -1 (this Employee ordered first)
     * or 1 (the other Employee ordered first) 
     */
    public int compareTo(Employee other) {
        String key = username + password;
        String otherKey = other.username + other.password;
        
    	if (key.compareTo(otherKey) == 0) {
    		return 0;
    	}
    	else if (key.compareTo(otherKey) > 0) {
    		return 1;
    	}
    	else {
    		return -1;
    	}
    }
    	
    
    /**
     * Returns a consistent hash code for 
     * each Movie by summing the Unicode values
     * of each character in the key
     * Key = title + director
     * @return the hash code
     */
	@Override public int hashCode() {
		String output = username+password; 
		int result = 0; 
		
		for (int i = 0; i < output.length(); i++)
		{
			result += output.charAt(i); 
		}
		
		return result;
	}
}
