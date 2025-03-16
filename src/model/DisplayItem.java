package model;
//GROWING PAINS - Mark Lambert - C00192497
//DisplayItem subclass of Item

/**
 * The DisplayItem class extends the Item class, used for displaying products 
 * in the catalogue on the screen
 */
public class DisplayItem extends Item {

    //Default constructor
	
	/**
	 * Default Constructor
	 */
    public DisplayItem() {
    	
    }

    /**
     * Constructs a DisplayItem object for Retrieval
     * 
     * @param itemID The ID of the Accessory
     * @param itemName The Name of the Accessory
     * @param description The Description of the Accessory
     * @param price The Price of the Accessory
     * @param qty The Quantity of the Accessory
     * @param image_path The Image Path of the Accessory
     */
    public DisplayItem(int itemID, String itemName, String description, double price, int qty, String image_path)  {
    	super(itemID, itemName, description, price, qty, image_path);

    }
    
    /**
     * Constructs a DisplayItem object for Retrieval
     * 
     * @param itemName The Name of the Accessory
     * @param description The Description of the Accessory
     * @param price The Price of the Accessory
     * @param qty The Quantity of the Accessory
     * @param image_path The Image Path of the Accessory
     */
    public DisplayItem(String itemName, String description, double price, int qty, String image_path)  {
    	super(itemName, description, price, qty, image_path);

    }
	@Override
	public String getType() {
		// TODO Auto-generated method stub
		return null;
	}


    
}