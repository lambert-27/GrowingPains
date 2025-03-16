package model;
//GROWING PAINS - Mark Lambert - C00192497
//Accessory subclass of Item

/**
 * The Accessory class is a sublclass of the Item class. It holds all information about an Accessory item
 * and provides functionality specific to Accessories.
 * 
 * The Accessory class adds the 'accessoryType' field to represent the type of accessory it is
 */
public class Accessory extends Item {
	//Class instance variables
    private String accessoryType;
    //Constructors
    //Default constructor for the Accessory class
    public Accessory() {
    	
    }
    
    /**
     * Constructor used for creating an Accessory Object for RETRIEVAL operations
     * hence, the inclusion of itemID
     * @param itemID The ID of the Accessory
     * @param itemName The Name of the Accessory
     * @param description The Description of the Accessory
     * @param price The Price of the Accessory
     * @param qty The Quantity of the Accessory
     * @param image_path The Image Path of the Accessory
     */
    public Accessory(int itemID, String itemName, String description, double price, int qty, String image_path)  {
    	super(itemName, description, price, qty, image_path);
    	//Calls the super class' setType method, with the argument being passed taken from the getType for current class
    	super.setType(getType());
    }
    
    /**
     * Constructor used for creating an Accessory Object for INSERTION operations
     * hence, the absence of itemID
     * @param itemName The Name of the Accessory
     * @param description The Description of the Accessory
     * @param price The Price of the Accessory
     * @param qty The Quantity of the Accessory
     * @param image_path The Image Path of the Accessory
     */
    public Accessory(String itemName, String description, double price, int qty, String accessoryType, String image_path)  {
    	super(itemName, description, price, qty, image_path);
    	setAccessoryType(accessoryType);
    	//Calls the super class' setType method, with the argument being passed taken from the getType for current class
    	super.setType(getType());
    }

    //Setters
    
    /**
     * Sets the type of Accessory (e.g Pot, Tool etc)
     * @param accessoryType The type of Accessory to be set
     * 
     */
    public void setAccessoryType(String accessoryType) {
    	this.accessoryType = accessoryType;
    }
    
    /**
     * Gets the type of Product (e.g Plant or Accessory)
     * @return The type of Product (Plant or Accessory)
     * 
     */
    public String getType() {
    	//Accessory is a subclass of Object, calling .getSimpleName() removes the package name
    	return this.getClass().getSimpleName();
    }
    /**
     *  Gets the type of Accessory (e.g Pot, Tool etc)
     * return The type of Accessory (e.g Pot, Tool etc)
     */
    public String getAccessoryType() {
    	return this.accessoryType;
    } 
}