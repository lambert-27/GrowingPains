//GROWING PAINS - Mark Lambert - C00192497
//Item abstract Class - 
package model;

public abstract class Item {
	//TODO Ask Jason about the use of setting up a static variable for each 
	//object which will be placed into a DB, so that it corresponds with the auto increment

    private int itemID;
	private String itemName;
	private String description;
	private double price;
	private int qty;
	private String type;
	//Constructors
	//Default constructor
	public Item() {
		
	}
	//Item - INSERTION
    public Item(String itemName, String description, double price, int qty) {
    	setItemName(itemName);
    	setDescription(description);
    	setPrice(price);
    	setQty(qty);
    	setType(type);
    }
    
    //Item constructor containing itemID for Item RETRIEVAL
    public Item(int itemID, String itemName, String description, double price, int qty) {
    	setItemID(itemID);
    	setItemName(itemName);
    	setDescription(description);
    	setPrice(price);
    	setQty(qty);
    	setType(type);
    }

    //Setters for super class attributes (Item)
    public void setItemName(String itemName) {
    	//Note call of super.variableName - Calls the Item class attribute 
    	this.itemName = itemName;
    }
    
    public void setType(String type) {
    	this.type = type;
    }
    
    public void setPrice(double price) {
    	this.price = price;
    }
    
    public void setDescription(String description) {
    	this.description = description;
    }
    
    public void setQty(int qty) {
    	this.qty = qty;
    }
    
    public void setItemID(int itemID) {
    	this.itemID = itemID;
    }
    
    //this class getterss
    public String getItemName() {
    	return this.itemName;
    }
    
    public abstract String getType();
    
    public double getPrice() {
    	return this.price;
    }
    
    public String getDescription() {
    	return this.description;
    }
    
    public int getQty() {
    	return this.qty;
    }
    
    public int getItemID() {
    	return this.itemID;    }
	@Override
	public String toString() {
		return "Item [itemID=" + itemID + ", itemName=" + itemName + ", description=" + description + ", price=" + price
				+ ", qty=" + qty + ", type=" + type + "]";
	}

    
}