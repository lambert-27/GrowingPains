package model;
//GROWING PAINS - Mark Lambert - C00192497
//Cart class
import java.util.ArrayList;
import java.util.List;

/**
 * The Cart class holds all information relative to each Customers Cart
 * The Account class holds the totalPrice and a List of all orderItems within the customers Cart
 */
public class Cart {
	private List<OrderItem> items;
    private double totalPrice;

    /**
     * Constructor used for creating a Cart Object 
     * The List of items gets initialised in this Constructor and the totalPrice gets set to 0 initially.
     */
    public Cart() {
    	this.items = new ArrayList<OrderItem>();
    	setTotalPrice(0);
    }
    
    /**
     * Gets the List of orderItems associated with the Customer
     * @return A List of orderItems containing information about each product in the cart
     */
    public List<OrderItem> getCart(){
    	return this.items;
    }
    
    /**
     * Adds an item to the cart
     * First, the method checks if the Item being added is already in the Customer's cart
     * or if the Item needs to be added freshly to the cart
     * @param item The Item which is being added to the cart
     * @param qty The amount of the Item being added to the cart
     */
    public void addItem(Item item, int qty) {
    	boolean isFound = false;
    	
    	for(OrderItem orderItem : items) {
//    		If the user already has the item in their cart, then we need only increment the current qty in the cart by the qty they intend to add
    		if(item.getItemID() == orderItem.getItemID()) {
    			orderItem.setQty(orderItem.getQty()+qty);
    			isFound = true;
    		}
    	}
    	
    	if(isFound == false) {
    		OrderItem orderItem = new OrderItem(item, qty);
    		items.add(orderItem);
    	}
    	
//    	Update the current price by passing in the price of the item time the qty of the item
    	updatePrice(item.getPrice() * qty);
    }
    
    /**
     * Update the total price of the Cart based on incrementing the price 
     * 
     * @param itemPrice The price associated with the Item
     */
    public void updatePrice(double itemPrice) {
    	this.totalPrice += itemPrice;
    }
    
    /**
     * Update the total price of the Cart based on decrementing the Price
     * 
     * @param itemPrice The price associated with the Item
     */
    public void updatePriceDec(double itemPrice) {
    	this.totalPrice -= itemPrice;
    }
    
    
    /**
     * Set the total price of the Cart
     * 
     * @param totalPrice The price total price of the cart
     */
    public void setTotalPrice(double totalPrice) {
    	this.totalPrice = totalPrice;
    }
    
    
    /**
     * Gets the total price of the Cart
     * 
     * @return The price associated with the Item
     */
    public double getTotalPrice() {
    	return totalPrice;
    }
    
    /**
     * Resets the content of the cart back to zero
     */
    public void clearCart() {
    	this.items.clear();
    	this.totalPrice = 0;
    }
}