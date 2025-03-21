//GROWING PAINS - Mark Lambert - C00192497
//Item abstract Class - 
package model;

/**
 * The Item class is an abstract class, holding all  broad information about an item
 * and provides functionality relevant to all Items
 * 
 * The item class holds the itemID, itemName, description, price, quantity, type and image path of each product
 */
public abstract class Item {

    private int itemID;
	private String itemName;
	private String description;
	private double price;
	private int qty;
	private String type;
	private String image_path;
	//Constructors
	//Default constructor
	public Item() {
		
	}
    /**
     * Constructor used for creating an Item Object for INSERTION operations
     * hence, the absence of itemID
     * @param itemName The Name of the Product
     * @param description The Description of the Product
     * @param price The Price of the Product
     * @param qty The Quantity of the Product
     * @param image_path The Image Path of the Product
     */
    public Item(String itemName, String description, double price, int qty, String image_path) {
    	setItemName(itemName);
    	setDescription(description);
    	setPrice(price);
    	setQty(qty);
    	setType(type);
    	setImgPath(image_path);
    }
    
    /**
     * Constructor used for creating an Product Object for RETRIEVAL operations
     * hence, the inclusion of itemID
     * @param itemID The ID of the Product
     * @param itemName The Name of the Product
     * @param description The Description of the Product
     * @param price The Price of the Product
     * @param qty The Quantity of the Product
     * @param image_path The Image Path of the Product
     */
    public Item(int itemID, String itemName, String description, double price, int qty, String image_path) {
    	setItemID(itemID);
    	setItemName(itemName);
    	setDescription(description);
    	setPrice(price);
    	setQty(qty);
    	setType(type);
    	setImgPath(image_path);
    }

    /**
     * Sets the name of the Item
     * 
     * @param itemName The name of the Product
     */
    public void setItemName(String itemName) {
    	this.itemName = itemName;
    }
    /**
     * Sets the type of the Item
     * 
     * @param type The type of the Product
     */
    public void setType(String type) {
    	this.type = type;
    }
    /**
     * Sets the Price of the Item
     * 
     * @param price The price associated with the Product
     */
    public void setPrice(double price) {
    	this.price = price;
    }
    /**
     * Sets the description of the Item
     * 
     * @param description The name of the Product
     */
    public void setDescription(String description) {
    	this.description = description;
    }
    /**
     * Sets the Quantity of the Product
     * 
     * @param qty The quantity associated with the Product
     */
    public void setQty(int qty) {
    	this.qty = qty;
    }
    
    /**
     * Sets the ID of the Item
     * 
     * @param itemID The ID of the Product
     */
    public void setItemID(int itemID) {
    	this.itemID = itemID;
    }
    /**
     * Gets the Image Path of the Item
     * 
     * @return The Image Path associated with the Product
     */
    public void setImgPath(String image_path) {
    	this.image_path = image_path;
    }
    
    /**
     * Get the name of the Item
     * 
     * @return Name The name of the Product
     */
    public String getItemName() {
    	return this.itemName;
    }
    
    /**
     * Gets the Image Path of the Item
     * 
     * @return The Image Path associated with the Product
     */
    public String getImgPath() {
    	return this.image_path;
    }
    
    /**
     * Abstract method getType used by child class' of Item
     * as Item won't be instantiated (due to it being an abstract class)
     */
    public abstract String getType();
    
    /**
     * Gets the Price of the Item
     * 
     * @return The price associated with the Product
     */
    public double getPrice() {
    	return this.price;
    }
    
    /**
     * Gets the description of the Item
     * 
     * @return description The name of the Product
     */
    public String getDescription() {
    	return this.description;
    }
    
    /**
     * Gets the Quantity of the Product
     * 
     * @return qty The quantity associated with the Product
     */
    public int getQty() {
    	return this.qty;
    }
    
    /**
     * Gets the ID of the Item
     * 
     * @return itemID The ID of the Product
     */
    public int getItemID() {
    	return this.itemID;   
    }
 
}