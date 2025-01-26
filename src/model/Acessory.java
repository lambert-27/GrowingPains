package model;

public class Acessory extends Item {

    private String accessoryType;

    public Acessory(String itemName, String description, float price, int qty, String accessoryType)  {
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
    	return this.getClass().getSimpleName();
    }
    //Getters
    public String getAccessoryType() {
    	return this.accessoryType;
    }
    
}