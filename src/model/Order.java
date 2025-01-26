package model;

import java.sql.Time;
import java.sql.Date;
import java.util.Calendar;

public class Order {
    //private int orderID;
    private Date date;
    private Time time;
    private Address shippingAdrs;
    private float totalPrice;
    
    public Order() {
    	//TODO ask Jason about the order table, do I have a productID in there or do I not? 
    	//Calls setDate and setTime without arguments as it auto timestaps to when Order is created
    	setDate();
    	setTime();
    	setAddress(shippingAdrs);
    	setPrice(totalPrice);
    }

    //Setters
    public void setDate() {
    	this.date = new Date(System.currentTimeMillis());
    }

    public void setTime() {
    	this.time = new Time(System.currentTimeMillis());
    }
    
    public void setAddress(Address shippingAdrs) {
    	this.shippingAdrs = shippingAdrs;
    }
    
    public void setPrice(float totalPrice) {
    	this.totalPrice = totalPrice;
    }
}