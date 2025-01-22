package model;

import java.sql.Time;
import java.sql.Date;

/**
 * 
 */
public class Order {


    private int orderID;
    private Date date;
    private Time time;
    private Address shippingAdrs;
    private float totalPrice;
    
    public Order() {
    }


}