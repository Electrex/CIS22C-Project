package Modules;

import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * Orders class - containing order information and customer shipping preferences
 * 
 * @author Mia Skinner
 */
public class Order implements Comparable<Order> {

	private static int orderIDGen = 1000;
	private int orderID;
	private String customerName;
	private Timestamp orderDate;
	private Timestamp shipDate;
	private ArrayList<Product> product;
	private ArrayList<Integer> quantity;
	private String shipmentType;
	private boolean isShipped;
	private static final SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy HH:mm:ss");

	/**
	 * Helper function for compareTo() to compute the orderScore
	 * 
	 * @return int value for shipping speed
	 */
	private int shipSpeedHelper() {
		int OVERNIGHT = 10;
		int RUSH = 5;
		int STANDARD = 1;

		int shipSpeed;
		if (this.shipmentType.equalsIgnoreCase("Overnight")) {
			shipSpeed = OVERNIGHT;
		} else if (this.shipmentType.equalsIgnoreCase("Rush")) {
			shipSpeed = RUSH;
		} else {
			shipSpeed = STANDARD;
		}
		return shipSpeed;
	}

	/**
	 * Helper function for compareTo() to compute the orderScore
	 * 
	 * @return the number of days since the order was placed
	 */
	private int daysOldHelper() {
		Date orderdate;
		int daysOld = 0;
		try {
			orderdate = sdf.parse(this.getOrderDate());
			Date now = new Date(System.currentTimeMillis());
			long diff = now.getTime() - orderdate.getTime();
			daysOld = (int) TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);

		} catch (ParseException e) {
			System.out.println("daysOldHelper(): Error parsing orderDate. " + e.getMessage());
		}
		return daysOld;
	}

	/* CONSTRUCTORS */

    /**
     * Default constructor for the Order class
     * Generates orderID (sequence #), sets order date to present time and date,
     * and sets shipDate, product, quantity, and shipmentType to null, customerName to "", and isShipped to false.
     */
	public Order() {
		orderID = ++orderIDGen;
		customerName = "";
		orderDate = new Timestamp(System.currentTimeMillis());
		shipDate = null;
		product = null;
		quantity = null;
		shipmentType = null;
		isShipped = false;
	}

    /**
     * Constructor for the Order class
     * @param prod, the Order's Product ArrayList<Product>
     * @param qty, the Order's parallel product quantity ArrayList<Integer>
     * @param custName, the Customer's name (String) 
     * @param shipType, shipment type selected by customer (Standard, Rush, Overnight)
     */
	public Order(ArrayList<Product> prod, ArrayList<Integer> qty, String custName, String shipType) {
		orderID = ++orderIDGen;
		customerName = custName;
		orderDate = new Timestamp(System.currentTimeMillis());
		shipDate = null;
		product = prod;
		quantity = qty;
		shipmentType = shipType;
		isShipped = false;
	}

	 /**
     * Constructor for the Order class
     * @param prod, the Order's Product ArrayList<Product>
     * @param qty, the Order's parallel product quantity ArrayList<Integer>
     * @param custName, the Customer's name (String) 
     * @param orderDate, Timestamp of when the Order was placed
     * @param shipDate, Timestamp of when the Order was shipped
     * @param shipType, shipment type selected by customer (Standard, Rush, Overnight)
     * @param isshipped, boolean if Order has already been shipped
     */
	public Order(int orderid, ArrayList<Product> prod, ArrayList<Integer> qty, String custName, 
			Timestamp orderdate, Timestamp shipdate, String shipType, boolean isshipped) {
		orderID = orderid;
		customerName = custName;
		orderDate = orderdate;
		shipDate = shipdate;
		product = prod;
		quantity = qty;
		shipmentType = shipType;
		isShipped = isshipped;
	}

	/* ACCESSORS */

	/**
	 * Returns the Order ID as an int
	 * @return orderID
	 */
	public int getOrderID() {
		return orderID;
	}

	/**
	 * Returns the Customer's Name as a String
	 * @return customerName
	 */
	public String getCustomerName() {
		return customerName;
	}

	/**
	 * Returns the order date as a formatted date string 
	 * @return orderDate
	 */
	public String getOrderDate() {
	    if(orderDate == null)
	    {
	        return "";
	    }
	    else
	    {
	        return sdf.format(orderDate);
	        // return orderDate;
	    }
	}

	/**
	 * Returns the ship date as a formatted date string
	 * 
	 * @return shipDate
	 */
	public String getShipDate() {
	    if(shipDate == null)
	    {
	        return "Not shipped yet";
	    }
	    else
	    {
	        return sdf.format(shipDate);
	        // return shipDate;
	    }
	}

	/**
	 * Returns the products being ordered as a Product ArrayList (parallel to
	 * getQuantity)
	 * 
	 * @return product
	 */
	public ArrayList<Product> getProduct() {
		return product;
	}

	/**
	 * Returns the quantities being ordered as an Integer ArrayList (parallel to
	 * getProduct)
	 * 
	 * @return quantity
	 */
	public ArrayList<Integer> getQuantity() {
		return quantity;
	}

	/**
	 * Returns the shipment type as a String ("Standard", "Rush",
	 * or "Overnight")
	 * 
	 * @return shipmentType
	 */
	public String getShipmentType() {
		return shipmentType;
	}

	/**
	 * Returns true if this Order has been shipped already, and false otherwise
	 * 
	 * @return isShipped
	 */
	public boolean getIsShipped() {
		return isShipped;
	}

	/* MUTATORS */

	/**
	 * Sets the orderID to be something other than the default, sequentially
	 * generated value
	 * 
	 * @param orderID (int)
	 */
	public void setOrderID(int orderID) {
		this.orderID = orderID;
	}

	/**
	 * Sets the Customer's Name
	 * 
	 * @param custName (String)
	 */
	public void setCustomerName(String custName) {
		this.customerName = custName;
	}

	/**
	 * Sets Order date as something than the default of the time of when the Order
	 * was constructed
	 * 
	 * @param newDate (Timestamp)
	 */
	public void setOrderDate(Timestamp newDate) {
		this.orderDate = newDate;
	}

	/**
	 * Sets ship date as NOW (current time & date). Sets isShipped to true.
	 */
	public void setShipDate() {
		this.shipDate = new Timestamp(System.currentTimeMillis());
		isShipped = true;
	}

	/**
	 * Sets the product list being purchased by the customer
	 * 
	 * @param product (parallel ArrayList)
	 */
	public void setProduct(ArrayList<Product> product) {
		this.product = product;
	}

	/**
	 * Sets the quantities of each product being purchased by the customer
	 * 
	 * @param quantity (parallel ArrayList)
	 */
	public void setQuantity(ArrayList<Integer> quantity) {
		this.quantity = quantity;
	}

	/**
	 * If valid ship type is passed, it stores it as the ship type of the order and
	 * returns true. Otherwise, it returns false (ie not stored, invalid type).
	 * 
	 * @param ship_type
	 * @return true if accepted, false if rejected
	 */
	public boolean swapShipmentType(String ship_type) {
		if (ship_type.equalsIgnoreCase("Rush") || ship_type == "Overnight" || ship_type == "Standard") {
			shipmentType = ship_type;
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Toggles the isShipped boolean between true and false
	 */
	public void toggleIsShipped() {
		this.isShipped = !isShipped;
	}

	/* OTHER METHODS */
	
	/**
	 * Returns the total price of the Order (product prices * qtys)
	 * @precondition product and quantity arrays are the same length
	 * @throws IndexOutOfBoundsException if product and quantity arrays are not the same length
	 * @returns totalPrice (double) of Order
	 */
	public double totalOrderPrice() throws IndexOutOfBoundsException {
		//TODO check if need to add shipping cost as well 
		
		double totalPrice = 0;
		if (product.size() != quantity.size())
			throw new IndexOutOfBoundsException("getTotalOrderPrice(): Product and Quantity arrays are not of the same length!");
		
		for (int i = 0; i < product.size(); i++) {
			totalPrice += product.get(i).getUnitPrice() * quantity.get(i);
		}
		return totalPrice;
	}

	/**
	 * For each of the products/qty in the Order, creates a String with the 
     * the following format:
     * <orderDate>,<shipDate>,<shipmentType>,<customerName>,<productID>,<quantity>
	 */
	@Override
	public String toString() {
		String components = "";

		for (int j = 0; j < this.getProduct().size(); j++) {
			if (this.getIsShipped())
				components += "TRUE,";
			else
				components += "FALSE,";
			components += this.getOrderDate() + ",";
			components += this.getShipDate() + ",";
			components += this.getShipmentType() + ",";
			components += this.getCustomerName() + ",";
			components += this.getProduct().get(j).getProductId() + ",";
			components += this.getQuantity().get(j) + "\n";
		}
		return components;
	}

	/**
	 * Returns a consistent hash code for
     * each Order by summing the Unicode values
     * of each character in the key
     * Key = orderID + customerName
     * @return the hash code
	 */
	@Override
	public int hashCode() {
		String key = orderID + customerName; //define key for this class
		int sum = 0;
		for (int i = 0; i < key.length(); i++) {
			sum += (int) key.charAt(i);
	    }
	    return sum;
	}

    /**
     * Determines whether two Order objects are
     * equal by comparing each instance variable value
     * @param obj the second object
     * @return whether the Orders are equal
     */
	@Override
	public boolean equals(Object obj) {
		
		if (this == obj)
			return true;
		
		if (!(obj instanceof Order)) {
			return false;
		 }
		 
		Order other = (Order) obj;
		if (isShipped != other.isShipped)
			return false;
		
		if (orderDate == null) {
			if (other.orderDate != null)
				return false;
		} else if (!orderDate.equals(other.orderDate))
			return false;
		
		if (product == null) {
			if (other.product != null)
				return false;
		} else if (!product.equals(other.product))
			return false;
		
		if (quantity != other.quantity)
			return false;
		
		if (shipDate == null) {
			if (other.shipDate != null)
				return false;
		} else if (!shipDate.equals(other.shipDate))
			return false;
		
		if (shipmentType == null) {
			if (other.shipmentType != null)
				return false;
		} else if (!shipmentType.equals(other.shipmentType))
			return false;
		
		return true;
	}

	/**
	 * Compares two Order objects to determine priority ordering Returns 0 if the
	 * two items are equal Return -1 if this Order's OrderDate timestamp and ship
	 * speed comes before the other Order. Returns 1 if the other Order's OrderDate
	 * timestamp and ship speed comes before this Order. If the two Orders are the
	 * same, will return 0
	 * 
	 * @param the other Order object to compare to this
	 * @return 0 (same Order), -1 (this Order is lower priority) or 1 (this Order is
	 *         higher priority)
	 */
	@Override
	public int compareTo(Order otherOrder) {
		
		if (otherOrder == null) {
			return 1;
		}
		int orderScore = this.daysOldHelper() + this.shipSpeedHelper();
		// System.out.println("orderScore: " + orderScore);
		
		int otherOrderScore = otherOrder.daysOldHelper() + otherOrder.shipSpeedHelper();
		// System.out.println("otherOrderScore: " + otherOrderScore);
		if (orderScore < otherOrderScore) {
			return -1;
		} else if (orderScore > otherOrderScore) {
			return 1;
		} else { // scores are equal - compare timestamps

			Timestamp orderdate = null;
			Timestamp otherOrderDate = null;
			try {
				orderdate = new Timestamp(sdf.parse(this.getOrderDate()).getTime());
				otherOrderDate = new Timestamp(sdf.parse(otherOrder.getOrderDate()).getTime());
			} catch (ParseException e) {
				System.out.println("compareTo(): Error parsing dates. " + e.getMessage());
			}
			if (orderdate.before(otherOrderDate)) {
				return -1;
			} else if (orderdate.after(otherOrderDate)) {
				return 1;
			} else { // they have the exact same orderDate Timestamp and shipmentType
				return 0;
			}
		}

	}

}
