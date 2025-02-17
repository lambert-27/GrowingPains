package model;

import java.sql.Time;
import java.sql.Date;

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
    public Order() {}
    //Order - INSERTION
    public Order(int customerID, int productID, Address shippingAdrs, float totalPrice) {
    	setCustomerID(customerID);
    	setProductID(productID);
    	setDate();
    	setTime();
    	setAddress(shippingAdrs);
    	setPrice(totalPrice);
    }
    
    //Constructor including orderID, used for Order RETRIEVAL 
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
    public void setOrderID(int orderID) {
    	this.orderID = orderID;
    }
    public void setDate() {
    	this.date = new Date(System.currentTimeMillis());
    }

    public void setTime() {
    	this.time = new Time(System.currentTimeMillis());
    }
    
    public void setDate(Date date) {
    	this.date = date;
    }
    
    public void setTime(Time time) {
    	this.time = time;
    }
    
    public void setAddress(Address shippingAdrs) {
    	this.shippingAdrs = shippingAdrs;
    }
    
    public void setAddress(String shippingAdrs) {
    	this.shippingAdrs = new Address(shippingAdrs);
    }
    
    public void setPrice(float totalPrice) {
    	this.totalPrice = totalPrice;
    }
    
    public void setCustomerID(int customerID) {
    	this.customerID = customerID;
    }
    
    public void setProductID(int productID) {
    	this.productID = productID;
    }
    
    public Date getDate() {
    	return this.date;
    }
    
    public Time getTime() {
    	return this.time;
    }
    
    public String getAddress() {
    	return this.shippingAdrs.toString();
    }
    
    public float getPrice() {
    	return this.totalPrice;
    }
    
    public int getCustomerID() {
    	return this.customerID;
    }
    
    public int getProductID() {
    	return this.productID;
    }
	@Override
	public String toString() {
		return "Order [orderID=" + orderID + ", customerID=" + customerID + ", productID=" + productID + ", date="
				+ date + ", time=" + time + ", shippingAdrs=" + shippingAdrs + ", totalPrice=" + totalPrice + "]";
	}
    
    
}