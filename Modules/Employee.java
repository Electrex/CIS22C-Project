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
	
	public Employee(String fullname, String username, String password)
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
        String result = username + "," + password + "\n";
        return result;
    }
    
    /**
     * Determines whether two Movie objects are 
     * equal by comparing titles and directors
     * @param otherMovie the second Movie object
     * @return whether the Movies are equal
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
     * Compares two Movie objects to determine ordering
     * Returns 0 if the two items are equal
     * Return -1 if this Movie's title comes alphabetically
     * before the other Movie's title.
     * Returns 1 if the other Movie's title comes
     * alphabetically before this movie's title
     * If the two movie's titles are the same, will
     * differentiate by director's name (alphabetical
     * comparison)
     * @param the other Movie object to compare to this
     * @return 0 (same movie), -1 (this movie ordered first)
     * or 1 (the other movie ordered first) 
     */
    public int compareTo(Employee otherMovie) {
        String key = fullname + username;
        String otherKey = otherMovie.fullname + otherMovie.username;
        
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
