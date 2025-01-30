package model;

import java.sql.Time;
import java.sql.Date;
import java.util.Calendar;

public class Order {
	//Class instance variables
    private int orderID;
    private Date date;
    private Time time;
    private Address shippingAdrs;
    private float totalPrice;
    
    //Constructors
    //Order - INSERTION
    public Order() {
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