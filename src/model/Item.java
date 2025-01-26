package model;


public abstract class Item {
	//TODO Ask Jason about the use of setting up a static variable for each 
	//object which will be placed into a DB, so that it corresponds with the auto increment

//    protected int itemID;
	private String itemName;
	private String description;
	private float price;
	private int qty;
	private String type;
	
    public Item(String itemName, String description, float price, int qty) {
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
    
    public void setPrice(float price) {
    	this.price = price;
    }
    
    public void setDescription(String description) {
    	this.description = description;
    }
    
    public void setQty(int qty) {
    	this.qty = qty;
    }
    
    //this class getterss
    public String getItemName() {
    	return this.itemName;
    }
    
    public abstract String getType();
    
    public float getPrice() {
    	return this.price;
    }
    
    public String getDescription() {
    	return this.description;
    }
    
    public int getQty() {
    	return this.qty;
    }


}