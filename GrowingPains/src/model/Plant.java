package model;
//GROWING PAINS - Mark Lambert - C00192497
//Plant subclass of Item


/**
 * The Accessory class is a sublclass of the Item class. It holds all information about an Accessory item
 * and provides functionality specific to Accessories.
 * 
 * The Accessory class adds the 'accessoryType' field to represent the type of accessory it is
 */
public class Plant extends Item {
	
//	TODO Modify SQL Product table to include species, difficulty and other additional info to be kept about an item, then for the QUERY of each class, depending on what
	//class, we call only for the relevant elements, eg getAllPlants would query only for species, difficulty, not accessoryType for example
	//Class instance variables
    private String species;
    private String difficulty;

    //Constructors
    /**
     * Default Constructor
     */
    public Plant() {
    	
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
    public Plant(int itemID, String itemName, String description, double price, int qty, String image_path) {
    	//Calls super class constructor
    	super(itemName, description, price, qty,  image_path);
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
     * @param difficulty The difficulty level of the plant
     * @param species The species of the plant
     * 
     */
    public Plant(String itemName, String description, double price, int qty, String species, String difficulty, String image_path) {
    	//Calls super class constructor
    	super(itemName, description, price, qty, image_path);
    	super.setType(getType());
    	setSpecies(species);
    	setDifficulty(difficulty);
    }
    
//    public Plant(int itemID, String itemName, String description, double price, int qty, String species, String difficulty, String image_path) {
//    	//Calls super class constructor
//    	super(itemID, itemName, description, price, qty, image_path);
//    	super.setType(getType());
//    	setSpecies(species);
//    	setDifficulty(difficulty);
//    }

    //Setters
    /**
     * Sets the spcies of Plant (e.g Cactus, Succulent etc)
     * @param species The species of Plant to be set
     * 
     */
    public void setSpecies(String species) {
    	this.species = species;
    }
    /**
     * Sets the difficulty of Plant 
     * @param difficulty The difficulty of Plant to be set
     * 
     */
    public void setDifficulty(String difficulty) {
    	this.difficulty = difficulty;
    }
    
    //Getters
    /**
     * Gets the spcies of Plant (e.g Cactus, Succulent etc)
     * @return The species of Plant 
     * 
     */
    public String getSpecies() {
    	return species;
    }
    /**
     * Gets the difficulty of Plant 
     * @return The difficulty of the Plant
     * 
     */
    public String getDifficulty() {
    	return difficulty;
    }
    
    /**
     * getType() method which invokes the getClass() from superclass Object
     * @return The name of the Class
     */
    public String getType() {
    	//Accessory is a subclass of Object, calling .getSimpleName() removes the package name
    	return this.getClass().getSimpleName();
    }
    


}