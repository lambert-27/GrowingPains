package model;
//GROWING PAINS - Mark Lambert - C00192497
//Accessory subclass of Item

public class DisplayItem extends Item {
	//Class instance variables
    private String accessoryType;
    //Constructor
    //Default constructor
    public DisplayItem() {
    	
    }
    //TEMP RETRIEVAL
    public DisplayItem(int itemID, String itemName, String description, double price, int qty, String image_path)  {
    	super(itemName, description, price, qty, image_path);

    }
    
    public DisplayItem(String itemName, String description, double price, int qty, String image_path)  {
    	super(itemName, description, price, qty, image_path);

    }
	@Override
	public String getType() {
		// TODO Auto-generated method stub
		return null;
	}


    
}