package Modules;
//Yusuf
public class Product implements Comparable<Product>{
    private double unitPrice;
    private String name;
    private String productId;
    private String description;
    private String manufacturer;
    private double cost;

    /**
     * Constructor for the Product class
     * @param name the Product's name
     * @param productId the Product's productId
     * @param unitPrice the unitPrice the Product was released
     * @param cost the amount costs
     */
    public Product(String name, String productId, double cost, double unitPrice, String manufacturer, String description) {
        this.unitPrice = unitPrice;
        this.name = name;
        this.productId = productId;
        this.cost = cost;
        this.description = description;
        this.manufacturer = manufacturer;
    }

    /**
     * Accesses the description of the Product
     * @return the Product's name
     */
    public String getManufacturer() {
        return manufacturer;
    }

    /**
     * Accesses the description of the Product
     * @return the Product's name
     */
    public String getDescription() {
        return description;
    }

    /**
     * Accesses the name of the Product
     * @return the Product's name
     */
    public double getUnitPrice() {
        return unitPrice;
    }

    /**
     * Accesses the productId of the Product
     * @return the Product's productId
     */
    public String getName() {
        return name;
    }

    /**
     * Access the unitPrice of the Product
     * @return the unitPrice of the Product
     */
    public String getProductId() {
        return productId;
    }

    /**
     * Access the cost of the Product in dollars
     * @return the Product's cost in dollars
     */
    public double getCost() {
        return cost;
    }

    /**
     * Sets the name of the Product
     * @param name the Product's name
     */
    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    /**
     * Sets the productId of the Product
     * @param productId the Product's productId
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Sets the unitPrice of the Product
     * @param unitPrice the unitPrice of the Product
     */
    public void setProductId(String productId) {
        this.productId = productId;
    }

    /**
     * Sets the cost of the Product
     * in dollars
     * @param cost the cost of the Product
     * in dollars
     */

    /**
     * Sets the description of the Product
     * @return the Product's name
     */
    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    /**
     * Sets the description of the Product
     * @return the Product's name
     */
    public void setDescription(String description) {
        this.description = description;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    /**
     * Formats the Product for display, using
     * the following format:
     * <name>,<productId>,<unitPrice>,<description>,<manufacturer>,<cost>
     */
    @Override public String toString() {
        String result = name
                + "," + productId
                + "," + cost
                + "," + unitPrice
                + "," + description
                + "," + manufacturer + "\n";
        return result;
    }

    /**
     * Determines whether two Product objects are
     * equal by comparing names and productIds
     * @param otherProduct the second Product object
     * @return whether the Products are equal
     */
    @Override public boolean equals(Object o) {
        return ((Product)o).getName().equals(name)
                && ((Product)o).getProductId().equals(productId);
    }

    /**
     * Compares two Product objects to determine ordering
     * Returns 0 if the two items are equal
     * Return -1 if this Product's name comes alphabetically
     * before the other Product's name.
     * Returns 1 if the other Product's name comes
     * alphabetically before this Product's name
     * If the two Product's names are the same, will
     * differentiate by product's productIds (alphabetical
     * comparison)
     * @param the other Product object to compare to this
     * @return 0 (same Product), -1 (this Product ordered first)
     * or 1 (the other Product ordered first)
     */
    public int compareTo(Product otherProduct) {
        if (this.equals(otherProduct))
            return 0;
        else if (name.compareTo(otherProduct.getName()) < 0)
            return -1;
        else if (name.compareTo(otherProduct.getName()) > 0)
            return 1;
        else {
            if (productId.compareTo(otherProduct.getProductId()) < 0)
                return -1;
            else
                return 1;
        }
    }

    /**
     * Compares two Product objects by primary key to determine ordering
     * Returns 0 if the two items are equal
     * Return -1 if this Product's name comes alphabetically
     * before the other Product's name.
     * Returns 1 if the other Product's name comes
     * alphabetically before this Product's name
     * @param the other Product object to compare to this
     * @return 0 (same Product), -1 (this Product ordered first)
     * or 1 (the other Product ordered first)
     */
    public int compareToPrimary(Product otherProduct) {
        if (name.compareTo(otherProduct.getName()) == 0)
            return 0;
        else if (this.name.compareTo(otherProduct.getName()) < 0)
            return -1;
        else // if (name.compareTo(otherProduct.getName()) > 0)
            return 1;
    }

    /**
     * Compares two Product objects by secondary key to determine ordering
     * Returns 0 if the two items are equal
     * Return -1 if this Product's productid comes alphabetically
     * before the other Product's productid.
     * Returns 1 if the other Product's productId comes
     * alphabetically before this Product's productId
     * @param the other Product object to compare to this
     * @return 0 (same Product), -1 (this Product ordered first)
     * or 1 (the other Product ordered first)
     */
    public int compareToSecondary(Product otherProduct) {
        if (productId.compareTo(otherProduct.getProductId()) == 0)
            return 0;
        else if (productId.compareTo(otherProduct.getProductId()) < 0)
            return -1;
        else //(productId.compareTo(otherProduct.getProductId()) < 0)
            return 1;
	}

    /**
     * Returns a consistent hash code for
     * each Product by summing the Unicode values
     * of each character in the key
     * Key = name + productId
     * @return the hash code
     */
    @Override public int hashCode() {
        String key = name + productId; //define key for this class
        int sum = 0;
        for (int i = 0; i < key.length(); i++) {
            sum += (int) key.charAt(i);
        }
        return sum;
    }

}