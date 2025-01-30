package model;
//GROWING PAINS - Mark Lambert - C00192497
//Accessory subclass of Item

public class Accessory extends Item {
	//Class instance variables
    private String accessoryType;
    //Constructor
    //Default constructor
    public Accessory() {
    	
    }
    //TEMP RETRIEVAL
    public Accessory(int itemID, String itemName, String description, double price, int qty)  {
    	super(itemName, description, price, qty);
    	//Calls the super class' setType method, with the argument being passed taken from the getType for current class
    	super.setType(getType());
    }
    
    public Accessory(String itemName, String description, double price, int qty, String accessoryType)  {
    	super(itemName, description, price, qty);
    	setAccessoryType(accessoryType);
    	//Calls the super class' setType method, with the argument being passed taken from the getType for current class
    	super.setType(getType());
    }

    //Setters
    public void setAccessoryType(String accessoryType) {
    	this.accessoryType = accessoryType;
    }
    
    //Method returns the class type of current class automatically
    public String getType() {
    	//Accessory is a subclass of Object, calling .getSimpleName() removes the package name
    	return this.getClass().getSimpleName();
    }
    //Getters
    public String getAccessoryType() {
    	return this.accessoryType;
    }
    
    
}