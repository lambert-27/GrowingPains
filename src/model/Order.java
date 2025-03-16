package model;
//GROWING PAINS - Mark Lambert - C00192497
//Order class 
import java.sql.Time;
import java.sql.Date;

/**
 * The Order class contains all information regarding an Order made by a customer
 * 
 * The Order class holds the orderID, customerID, productID, date, time, shippingAdrs and totalPrice 
 */

public class Order {
	//Class instance variables
    private int orderID;
    private int	customerID;
    private int productID;
    private Date date;
    private Time time;
    private Address shippingAdrs;
    private float totalPrice;
    
    //Constructors
    /* *
     * Default Constructor
     */
    public Order() {
    	
    }

    /**
     * Constructor for INSERTING an Order into database
     * 
     * @param customerID The ID of the Customer placing the order
     * @param productID A list of Product ID's in the Order
     * @param shippingAdrs The shipping address
     * @param totalPrice The total price of the order
     */
    public Order(int customerID, int productID, Address shippingAdrs, float totalPrice) {
    	setCustomerID(customerID);
    	setProductID(productID);
    	setDate();
    	setTime();
    	setAddress(shippingAdrs);
    	setPrice(totalPrice);
    }
    
    /**
     * Constructor for RETRIEVAL an Order into database
     * 
     * @param orderID The ID of the Order
     * @param customerID The ID of the Customer placing the order
     * @param productID A list of Product ID's in the Order
     * @param shippingAdrs The shipping address
     * @param totalPrice The total price of the order
     */
    public Order(int orderID, int customerID, int productID, Date date, Time time,String shippingAdrs, float totalPrice) {
    	setOrderID(orderID);
    	setCustomerID(customerID);
    	setProductID(productID);
    	setDate(date);
    	setTime(time);
    	setAddress(shippingAdrs);
    	setPrice(totalPrice);
    }
    

    //Setters
    
    /**
     * Sets the ID of the Order
     * 
     * @param orderID The ID of the Order
     */
    public void setOrderID(int orderID) {
    	this.orderID = orderID;
    }
    /**
     * Sets the date of the Order for INSERTION
     */
    public void setDate() {
    	this.date = new Date(System.currentTimeMillis());
    }

    /**
     * Sets the time of the Order for INSERTION
     */
    public void setTime() {
    	this.time = new Time(System.currentTimeMillis());
    }
    
    /**
     * Sets the date of the Order for RETRIEVAL
     * 
     * @param date The date of the Order
     */
    public void setDate(Date date) {
    	this.date = date;
    }
    
    /**
     * Sets the time of the Order for RETRIEVAL
     * 
     * @param time The time of the Order
     */
    public void setTime(Time time) {
    	this.time = time;
    }
    
    /**
     * Sets the Address of the Order
     * 
     * @param Address The Address of the Order
     */
    public void setAddress(Address shippingAdrs) {
    	this.shippingAdrs = shippingAdrs;
    }
    
    /**
     * Sets the Address of the Order
     * 
     * @param Address The Address of the Order
     */
    public void setAddress(String shippingAdrs) {
    	this.shippingAdrs = new Address(shippingAdrs);
    }
    
    /**
     * Sets the price of the Order
     * 
     * @param totalPrice The total price of the Order
     */
    public void setPrice(float totalPrice) {
    	this.totalPrice = totalPrice;
    }
    
    /**
     * Sets the customerID of the Order
     * 
     * @param customerID The ID of the customer placing the Order
     */
    public void setCustomerID(int customerID) {
    	this.customerID = customerID;
    }
    
    /**
     * Sets the list of ID's of the Order
     * 
     * @param productID The list of product ID's of the Order
     */
    public void setProductID(int productID) {
    	this.productID = productID;
    }
    /**
     * Gets the date of the Order for RETRIEVAL
     * 
     * @return The date of the Order
     */
    public Date getDate() {
    	return this.date;
    }
    /**
     * Gets the time of the Order for RETRIEVAL
     * 
     * @return The time of the Order
     */
    public Time getTime() {
    	return this.time;
    }
    /**
     * Gets the Address of the Order
     * 
     * @return The Address of the Order
     */
    public String getAddress() {
    	return this.shippingAdrs.toString();
    }
    /**
     * Gets the price of the Order
     * 
     * @return The total price of the Order
     */
    public float getPrice() {
    	return this.totalPrice;
    }
    /**
     * Gets the customerID of the Order
     * 
     * @return The ID of the customer placing the Order
     */
    public int getCustomerID() {
    	return this.customerID;
    }
    /**
     * Gets the list of ID's of the Order
     * 
     * @return The ID of the Order
     */
    public int getProductID() {
    	return this.productID;
    }
    /**
     * Gets the ID of the Order
     * 
     * @return The ID of the Order
     */
    public int getOrderID() {
    	return this.orderID;
    } 
}